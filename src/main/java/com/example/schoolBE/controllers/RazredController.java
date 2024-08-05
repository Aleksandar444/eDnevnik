package com.example.schoolBE.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.entities.Razred1Entity;
import com.example.schoolBE.repositories.RazredRepository;
import com.example.schoolBE.services.RazredService;

@RestController
@RequestMapping("/razred")
public class RazredController {
	private static final Logger logger = LoggerFactory.getLogger(RazredController.class);
	
	@Autowired
	private RazredService razredService;
	@Autowired
	private RazredRepository razredRepo;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih razreda
	public Iterable<Razred1Entity> getAll(){
		logger.info("Vracanje svih razreda iz baze");
		return razredRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value="/{razredId}") // vracanje razreda po ID
	public String getRazredById(@PathVariable int razredId) {
		logger.info("Vracanje odredjeni razred po id");
		try {
			return razredService.getRazredById(razredId);
		}catch(NotFoundException e) {
			logger.error("Razred nije pronadjen");
			return "Trazeni razred nije pronadjen";
		}
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public String addRazred(@RequestParam String imeRazreda) {
		logger.info("Dodavanje novog razreda u bazu");
		return razredService.addRazred(imeRazreda);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.PUT, value="/{razredId}")
	public String updateRazred(@PathVariable int razredId,@RequestParam String imeRazreda) throws NotFoundException {
		logger.info("Azuriranje razreda ");
		return razredService.updateRazred(razredId, imeRazreda);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE, value="/{razredId}")
	public void deleteRazred(@PathVariable int razredId) {
		logger.info("Brisanje razreda iz baze");
		razredRepo.deleteById(razredId);
	} 
}
