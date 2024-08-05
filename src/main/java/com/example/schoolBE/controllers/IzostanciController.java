package com.example.schoolBE.controllers;

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

import com.example.schoolBE.entities.IzostanciEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.IzostanciRepository;
import com.example.schoolBE.services.IzostanciService;

@RestController
@RequestMapping("/izostanci")
public class IzostanciController {
	private static final Logger logger = LoggerFactory.getLogger(IzostanciController.class);
	
	@Autowired
	private IzostanciService izostanciService;
	@Autowired
	private IzostanciRepository izostanciRepo;
	
	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<IzostanciEntity>> getAll(){
		Iterable<IzostanciEntity> izostanci  = izostanciService.getAll();
		logger.info("Prikazani su svi izostanci iz baze");
		return new ResponseEntity<>(izostanci, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.GET,value = "/{izostanakId}")
	public ResponseEntity<IzostanciEntity> getIzostanciById(@PathVariable int id){
		logger.info("Prikazan je izostanak sa id: {}");
		try {
			IzostanciEntity izostanak = izostanciService.getIzostanakById(id);
			return new ResponseEntity<>(izostanak, HttpStatus.OK);
		}catch(NotFoundException e) {
			logger.error("Izostanak za trazenim id-jem nije pronadjen");
			
		}return new ResponseEntity<IzostanciEntity>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<IzostanciEntity> addIzostanak(@RequestParam String opravdani,@RequestParam String neopravdani,
														@RequestParam String datumIzostanka,@RequestParam UcenikEntity ucenik){
		
		logger.info("Kreiran je novi izostanak ");
		try {
			IzostanciEntity izostanak = izostanciService.addIzostanak(opravdani, neopravdani, datumIzostanka, ucenik);
			return new ResponseEntity<>(izostanak,HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Greska prilikom dodavanja novog izostanka!");
			return new ResponseEntity<IzostanciEntity>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.PUT,value = "/{izostanakId}")
	public ResponseEntity<IzostanciEntity> updateIzostanak(@PathVariable int id,@RequestParam String opravdani,
															@RequestParam String neopravdani,
															@RequestParam String datumIzostanka,@RequestParam UcenikEntity ucenik)  throws NotFoundException{
		logger.info("Uspesno azuriranje zeljenog izostanka! ");
		try {
			IzostanciEntity izostanak = izostanciService.updateIzostanak(id, opravdani, neopravdani, datumIzostanka);
			return new ResponseEntity<>(izostanak, HttpStatus.OK);
			
		}catch(NotFoundException e ) {
			logger.error("Izostanak sa trazenim ID-jem nije pronadjen.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE, value = "/{izostanakId}")
	public void deleteIzostanak(@PathVariable int id) {
		logger.info("Uspecno brisnanje zeljenog izostanka! ");
		izostanciRepo.deleteById(id);
	}
	
	
	
	
}
