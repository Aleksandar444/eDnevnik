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

import com.example.schoolBE.entities.RoditeljEntity;
import com.example.schoolBE.repositories.RoditeljRepository;
import com.example.schoolBE.security.Views;
import com.example.schoolBE.services.RoditeljService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/roditelj")
public class RoditeljController {
	private static final Logger logger = LoggerFactory.getLogger(RoditeljController.class);
	@Autowired
	private RoditeljService roditeljService;
	@Autowired
	private RoditeljRepository roditeljRepo;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<RoditeljEntity> getAll(){
		logger.info("Vracanje svih roditelja iz baze");
		return roditeljService.getAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET,value = "/{roditeljId}") // prolanazenje roditelja po ID
	public String getRoditeljById(@PathVariable int roditeljId) {
		try {
			logger.info("Pronalazenje roditelja po id");
			return roditeljService.getRoditeljById(roditeljId);
		}catch (NotFoundException e){
			logger.error("Zeljeni roditelj nije pronadjen");
			return "Zeljeni roditelj nije pronadjen";
		}
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST) // dodavanje novog roditelja u bazu
	public String addRoditelj (@RequestParam String ime,@RequestParam String prezime,@RequestParam String email) {
		logger.info("Dodavanje novog roditelja");
		logger.info("Ime: {}, Prezime: {}, Email: {}", ime, prezime, email);
		return roditeljService.addRoditelj(ime, prezime, email);
	}
	
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.PUT,value ="/{roditeljId}")
	public String updateRoditelj(@PathVariable int roditeljId,@RequestParam String ime,@RequestParam String prezime,@RequestParam String email) {
		try {
			logger.info("Azuriranje odredjenog roditelja po id");
			return roditeljService.updateRoditelj(roditeljId, ime, prezime, email);
		}catch (NotFoundException e) {
			logger.error("Zeljeni roditelj nije pronadjen");
			return "Zeljeni roditelj nije pronadjen";
		}
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE,value ="/{roditeljId}")
	public void deleteRoditelj(@PathVariable Integer roditeljId) {
		logger.info("Brisanje roditelja iz baze");
		roditeljRepo.findById(roditeljId);
	}
}
