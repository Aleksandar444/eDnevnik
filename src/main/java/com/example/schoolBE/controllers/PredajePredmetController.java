package com.example.schoolBE.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.PredajePredmetEntity;
import com.example.schoolBE.repositories.PredajePredmetRepositoy;

@RestController
@RequestMapping(value="/predajePredmet")
public class PredajePredmetController {
	private static final Logger logger = LoggerFactory.getLogger(PredajePredmetController.class);
	@Autowired
	private PredajePredmetRepositoy predajePredmetRepo;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE , value = "/{predajePredmetId}") // brisanje predajePredmet id iz baze
	public void deletePredajePredmet(@PathVariable int predajePredmetId) {
		logger.info("Brisanje predajePredmet (nastavnikPredmet) ");
		predajePredmetRepo.deleteById(predajePredmetId);
	}
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih nastavnika iz baze
	public Iterable<PredajePredmetEntity> getAll(){
		logger.info("Vracanje svih predmet-nastavnik iz baze" );
		return predajePredmetRepo.findAll();
	}
}
