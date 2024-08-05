package com.example.schoolBE.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.dataaccess.entities.UserEntity;
import com.example.schoolBE.entities.RoditeljEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.UcenikRepository;
import com.example.schoolBE.services.UcenikService;

@RestController
@RequestMapping(value="/ucenik")
public class UcenikController {
	private static final Logger logger = LoggerFactory.getLogger(UcenikController.class);
	
	
	@Autowired
	private UcenikRepository ucenikRepo;
	@Autowired
	private UcenikService ucenikService;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST) // dodavanje novog ucenika u bazu
	public void addUcenik(@RequestParam String ime,@RequestParam String prezime,@RequestParam RoditeljEntity roditelj) {
		logger.info("Dodavanje novog ucenika");
		ucenikService.addUcenik(ime, prezime,roditelj);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // vracanje svih postojecih ucenika iz baze
	public Iterable<UcenikEntity> getAll(){
		logger.info("Vracanje svih ucenika");
		return ucenikRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET , value = "/{ucenikId}") // vracanje studenta sa odredjenim id
	public ResponseEntity<UcenikEntity> getUcenikById(@PathVariable int ucenikId) {
		logger.info("Vracanje odredjenog ucenika po id");
		 Optional<UcenikEntity> ucenik = ucenikService.findById(ucenikId);
		 return ucenik.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE , value = "/{ucenikId}") // brisanje ucenika iz baze
	public void deleteUcenik(@PathVariable int ucenikId) {
		logger.info("Brisanje ucenika iz baze");
		ucenikRepo.deleteById(ucenikId);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.PUT, value = "/{ucenikId}") // izmena podataka postojeceg ucenika
	public void updateUcenik(@PathVariable int ucenikId,@RequestParam String ime,@RequestParam String prezime,@RequestParam RoditeljEntity roditelj) {
		logger.info("Azuriranje ucenika");
		ucenikService.updateUcenik(ucenikId, ime, prezime,roditelj);
	}
	

}
