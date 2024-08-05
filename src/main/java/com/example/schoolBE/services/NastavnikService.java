package com.example.schoolBE.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.schoolBE.entities.KorisnikEntity;
import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.PredmetEntity;
import com.example.schoolBE.entities.RoditeljEntity;
import com.example.schoolBE.entities.PredajePredmetEntity;
import com.example.schoolBE.repositories.NastavnikRepository;
import com.example.schoolBE.repositories.PredmetRepository;
import com.example.schoolBE.repositories.PredajePredmetRepositoy;
import jakarta.transaction.Transactional;

@Service
public class NastavnikService {
	@Autowired
	private NastavnikRepository nastavnikRepo;
	
	@Autowired
	private PredmetRepository predmetRepo;
	
	@Autowired
	private PredajePredmetRepositoy predajePredmetRepo;
	
	public void dodajPredajePredmet(Integer nastavnikId,Integer predmetId) {
		NastavnikEntity nastavnik = nastavnikRepo.findById(nastavnikId)
				.orElseThrow();
		PredmetEntity predmet = predmetRepo.findById(predmetId)
				.orElseThrow();
		
		PredajePredmetEntity veza = new PredajePredmetEntity();
		veza.setNastavnik(nastavnik);
		veza.setPredmet(predmet);
		
		predajePredmetRepo.save(veza);
		
	}
	public NastavnikEntity dodajNastavnika(String ime,String prezime) {
		NastavnikEntity nastavnik = new NastavnikEntity();
		nastavnik.setIme(ime);
		nastavnik.setPrezime(prezime);
		nastavnikRepo.save(nastavnik);
		return nastavnik;
	}
	
	public String updateNastavnik(int nastavnikId,String ime,String prezime) throws NotFoundException {
		NastavnikEntity nastavnik = nastavnikRepo.findById(nastavnikId);
		if(nastavnik != null) {
			nastavnik.setIme(ime);;
			nastavnik.setPrezime(prezime);;
			nastavnikRepo.save(nastavnik);
			return "Nastavnik je uspesno izmenjen";
		}else {
			 throw new NotFoundException();
		}
	}
	public NastavnikEntity findNastavnikById(int nastavnikId) throws NotFoundException {
		NastavnikEntity nastavnik = nastavnikRepo.findById(nastavnikId);
		if (nastavnik == null) {
			throw new NotFoundException();
		}
		return nastavnik;
        
	}
	
}
