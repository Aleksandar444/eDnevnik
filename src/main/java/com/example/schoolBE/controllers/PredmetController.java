package com.example.schoolBE.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.PredmetEntity;
import com.example.schoolBE.repositories.PredmetRepository;
import com.example.schoolBE.services.PredmetService;

@RestController
@RequestMapping(value="/predmet")
public class PredmetController {
	private static final Logger logger = LoggerFactory.getLogger(PredmetController.class);
	@Autowired
	protected PredmetRepository predmetRepo;
	@Autowired
	protected PredmetService predmetService;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST) // dodavanje novog predmeta u bazu 
	public void  addPredmet(@RequestParam String imePredmeta,@RequestParam Integer nfc)  {
			logger.info("Dodavanje novog predmeta");
			predmetService.addPredmet(imePredmeta, nfc);
		
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih predmeta iz baze
	public Iterable<PredmetEntity> getAll(){
		logger.info("Vracanje svih predmeta iz baze");
		return predmetRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value = "/{predmetId}") // za get odredjenog predmeta po ID 
	public void getPredmetById(@PathVariable int predmetId){
		logger.info("Vracanje odredjeni predmet po ID");
		predmetService.getPredmetById(predmetId);
		
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.PUT, value = "/{predmetId}") // izmena podataka predmeta
	public void updatePredmet(@PathVariable int predmetId,@RequestParam String imePredmeta,@RequestParam Integer nfc) {
		logger.info("Azuriranje odredgjeni predmet po ID");
		predmetService.updatePredmet(predmetId, imePredmeta, nfc);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE,value = "/{predmetId}") // brisanje predmeta iz baze
	public void deletePredmet(@PathVariable int predmetId) {
		logger.info("Brisanje predmeta ");
		predmetRepo.deleteById(predmetId);
	}
}
