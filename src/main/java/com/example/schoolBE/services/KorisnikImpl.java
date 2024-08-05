package com.example.schoolBE.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.KorisnikEntity;
import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.repositories.KorisnikRepository;
@Service
public class KorisnikImpl  implements KorisnikService{
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	  @Override
	    public Iterable<KorisnikEntity> getAllUsers() {
	        return korisnikRepo.findAll();
	    }
	  
	  @Override
	    public Optional<KorisnikEntity> getUserById(int idKorisnika) throws NotFoundException {
	        Optional<KorisnikEntity> korisnik = korisnikRepo.findById(idKorisnika);
	        if (!korisnik.isPresent()) {
	            throw new NotFoundException();
	        }
	        return korisnik;
	    }
	  
	 
	    public KorisnikEntity addKorisnik(String korisnickoIme, String sifra, String uloga) {
		 
		  	KorisnikEntity korisnik = new KorisnikEntity();
		  	korisnik.setKorisnickoIme(korisnickoIme);
		  	korisnik.setSifra(sifra);
		  	korisnik.setUloga(uloga);
		  	korisnikRepo.save(korisnik);
		  	return korisnik;
	    }
	  
	  @Override
	    public String deleteUserById(int idKorisnika) throws NotFoundException {
	        Optional<KorisnikEntity> korisnik = korisnikRepo.findById(idKorisnika);
	        if (!korisnik.isPresent()) {
	            throw new NotFoundException();
	        }
	        korisnikRepo.deleteById(idKorisnika);
	        return "Korisnik sa ID " + idKorisnika + " je uspešno obrisan.";
	    }
	  
	  @Override
	    public String updateUser(String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika, String izmenjenaUloga) throws NotFoundException {
	        Optional<KorisnikEntity> korisnikOpt = korisnikRepo.findByKorisnickoIme(izmenjenoKorisnickoIme);
	        if (!korisnikOpt.isPresent()) {
	            throw new NotFoundException();
	        }
	        KorisnikEntity korisnik = korisnikOpt.get();
	        korisnik.setSifra(izmenjenaSifraKorisnika);
	        korisnik.setUloga(izmenjenaUloga);
	        korisnikRepo.save(korisnik);
	        return "Korisnik " + izmenjenoKorisnickoIme + " je uspešno ažuriran.";
	    }
	 
}
