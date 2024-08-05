package com.example.schoolBE.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.repositories.NastavnikRepository;
import com.example.schoolBE.services.*;

@RestController
@RequestMapping(value="/nastavnik")
public class NastavnikController {
	private static final Logger logger = LoggerFactory.getLogger(NastavnikController.class);
	
	@Autowired
	protected NastavnikRepository nastavnikRepo;
	
	@Autowired
	private NastavnikService nastavnikService;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method = RequestMethod.POST) // dodavanje novog nastavnika u bazu
	public void addNastavnik(@RequestParam String ime,@RequestParam String prezime) {
		logger.info("Kreiran je novi nastavnik ");
		nastavnikService.dodajNastavnika(ime, prezime);
		
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih nastavnika iz baze
	public Iterable<NastavnikEntity> getAll(){
		logger.info("Uspesno vracanje svih nastavnika! ");
		return nastavnikRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.GET,value = "/{nastavnikId}") // vracanje nastavnika po id
	public ResponseEntity<NastavnikEntity> findNastavnikById(@PathVariable int nastavnikId) throws NotFoundException {
		 try {
			 	logger.info("Trazenje odredjenog nastavnika po ID ");
	            NastavnikEntity nastavnik = nastavnikService.findNastavnikById(nastavnikId);
	            return ResponseEntity.ok(nastavnik);
	        } catch (NotFoundException e) {
	        	logger.error("Greska prilikom vracanja nastavnika sa zeljenim ID");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.PUT, value = "/{nastavnikId}") // izmena podataka nastavnika
	public void updateNastavnik(@PathVariable int nastavnikId,@RequestParam String ime,@RequestParam String prezime) throws NotFoundException {
		logger.info("Azuriranje zeljenog nastavnika! ");
		nastavnikService.updateNastavnik(nastavnikId, ime, prezime);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE , value = "/{nastavnikId}") // brisanje nastavnika iz baze
	public void deleteNastavnik(@PathVariable int nastavnikId) {
		logger.info("Brisanje zeljenog nastavnika ");
		nastavnikRepo.deleteById(nastavnikId);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/{nastavnikId}/predmet/{predmetId}") 
	public ResponseEntity<?> dodajPredmetNastavniku(@PathVariable Integer nastavnikId, @PathVariable Integer predmetId) {
		try {
			logger.info("Spajanje nastavnika i predmet koji predaje ");
			nastavnikService.dodajPredajePredmet(nastavnikId, predmetId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			logger.error("Greska prilikom spajanja nastavnika i predmet");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Greska prilikom dodavanja predmeta nastavniku: " + e.getMessage()) ;
		}
	}
	
	/*@PostMapping("/nastavnikPredajePredmet") // post  putem body (form-data)
	public ResponseEntity<String> nastavnikPredajePredmet(@RequestParam Integer nastavnikId,
														@RequestParam Integer predmetId){
		nastavnikService.dodajPredajePredmet(nastavnikId, predmetId);
		return ResponseEntity.ok("Nastavnik je povezan sa predmetom");
	}*/
}
