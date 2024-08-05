package com.example.schoolBE.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.entities.OcenaEntity;
import com.example.schoolBE.repositories.OcenaRepository;
import com.example.schoolBE.services.EmailService;
import com.example.schoolBE.services.OcenaService;

@RestController
@RequestMapping(value="/ocena")
public class OcenaController {
	private static final Logger logger = LoggerFactory.getLogger(OcenaController.class);
	@Autowired
	private OcenaService ocenaService;
	
	@Autowired
	private OcenaRepository ocenaRepo;
	
	@Autowired
	private EmailService emailService;
	
	/*@RequestMapping(method = RequestMethod.POST) // dodavanje novog nastavnika u bazu
	public void addNastavnik(@RequestParam String ime,@RequestParam String prezime) {
		nastavnikService.dodajNastavnika(ime, prezime);
		
	}*/
	/*@RequestMapping(method = RequestMethod.POST) // dodavanje nove ocene 
	public void dodajOcenu(@RequestParam int ocena) {
		ocenaService.dodajOcenu(ocena);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<OcenaEntity> getAll(){
	    return ocenaRepo.findAll();
	}*/
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<OcenaEntity> getAll(){
		logger.info("Vracanje svih ocena iz baze");
		return ocenaRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST, value ="/dodajOcenu")
	public ResponseEntity<String> addOcena(@RequestParam int ocena,@RequestParam int zakljucenaOcena,
											@RequestParam int prvoPolugodiste,@RequestParam int drugoPolugodiste,
											@RequestParam int vladanje,@RequestParam int usmeni,
											@RequestParam int pismeni,@RequestParam int nastavnikId,
											@RequestParam int predmetId,@RequestParam int ucenikId) {
		try {
			logger.info("Kreiranje nove ocene");
			String res = ocenaService.addOcena(ocena, zakljucenaOcena, prvoPolugodiste, drugoPolugodiste, vladanje, usmeni, pismeni, nastavnikId, ucenikId, predmetId);
			String studentName = "name";
			String parentEmail = "sallehtc55@gmail.com";
			String subject = "Nova ocena je dodata";
			String message = "Nastavnik -ime- je dodao novu ocenu za ucenika NN, iz predmeta -naziv-. Nova ocena: " + ocena + ", Nova zakljucna ocena: " + zakljucenaOcena;
			emailService.sendEmailToParent(studentName, parentEmail, subject, message);
			return ResponseEntity.status(HttpStatus.CREATED).body("Ocena je uspešno dodata i email poslat.");
		}catch (Exception e) {
			logger.error("Greska prilikom dodavanje nove ocene");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Doslo je do greske pri dodavanju ocene: " + e.getMessage());
	    }
	}
	
}
