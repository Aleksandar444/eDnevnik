package com.example.schoolBE.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.UcenikRazred;
import com.example.schoolBE.repositories.UcenikRazredRepository;
import com.example.schoolBE.services.UcenikRazredService;

@RestController
@RequestMapping(value="/ucenikRazred")
public class UcenikRazredController {
	
	private static final Logger logger = LoggerFactory.getLogger(UcenikRazredController.class);
	
	@Autowired
	private UcenikRazredService ucenikRazService;
	@Autowired
	private UcenikRazredRepository ucenikRazRepo;
	
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih 
	public Iterable<UcenikRazred> getAll(){
		logger.info("Vracanje sve ucenika sa njihovim razredima");
		return ucenikRazRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/{ucenikId}/dodajRazred/{razredId}") 
	public ResponseEntity<?> addUcenikRazred(@PathVariable Integer ucenikId, @PathVariable Integer razredId){
		try {
			logger.info("Dodavanje razred ucenik ");
			ucenikRazService.dodajUcenikRazred(ucenikId, razredId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			logger.error("Greska prilikom dodavanja razred ucenik");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Greska prilikom dodavanja ucenika razredu " + e.getMessage()) ;
		}
	}

}
