package com.example.schoolBE.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.entities.KorisnikEntity;
import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.KorisnikRepository;
import com.example.schoolBE.services.KorisnikService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(KorisnikController.class);
	
	@Autowired
	private  KorisnikService korisnikService;
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih korisnika iz baze
	public Iterable<KorisnikEntity> getAll(){
		logger.info("Uspesno vracanje svih korisnika iz baze! ");
		return korisnikRepo.findAll();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET,value = "/{korisnikId}") // vracanje korisnika po id
	public ResponseEntity<?> getUserById(@PathVariable int korisnikId) throws NotFoundException{
		 Optional<KorisnikEntity> korisnik = korisnikService.getUserById(korisnikId);
		 return korisnik.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public void addNewUser( @RequestParam String korisnickoIme,@RequestParam String sifraKorisnika, @RequestParam String uloga){
		logger.info("Dodavanje novog korisnika. ");
		korisnikService.addKorisnik(korisnickoIme, sifraKorisnika, uloga);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT, value = "/{korisnikId}") // menjanje korisnika
	public String updateUser(@PathVariable int korisnikId, @RequestParam @Valid String izmenjenoKorisnickoIme,
			@RequestParam String izmenjenaSifraKorisnika, String uloga) throws NotFoundException{
		logger.info("Uspesno azuriranje zeljenog korisnika! ");
		String response = korisnikService.updateUser(izmenjenoKorisnickoIme, izmenjenaSifraKorisnika, uloga);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE,value = "/{korisnikId}") // brisanje korisnika iz baze
	public void deleteKorisnik(@PathVariable int korisnikId) {
		logger.info("Uspesno brisanje zeljenog korisnika! ");
		korisnikRepo.deleteById(korisnikId);
	}
	
}
