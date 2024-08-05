package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.schoolBE.entities.PredmetEntity;
import com.example.schoolBE.repositories.PredmetRepository;

@Service
public class PredmetService {
	@Autowired
	private PredmetRepository predmetRepo;
	
	public PredmetEntity addPredmet(String imePredmeta,Integer nfc) {
		PredmetEntity predmet = new PredmetEntity();
		predmet.setImePredmeta(imePredmeta);
		predmet.setNedeljniFondCasova(nfc);
		predmetRepo.save(predmet);
		return predmet;
	}
	
	public PredmetEntity getPredmetById(int predmetId) {
		PredmetEntity predmet = predmetRepo.findById(predmetId);
		if (predmet == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Predmet ID not found");
		}
		return predmet;
	}
	
	public PredmetEntity updatePredmet(int predmetId,String imePredmeta,Integer nfc) {
		if (!predmetRepo.existsById(predmetId)) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Predmet not found");
		}
		PredmetEntity predmet = predmetRepo.findById(predmetId);
		predmet.setImePredmeta(imePredmeta);
		predmet.setNedeljniFondCasova(nfc);
		predmetRepo.save(predmet);
		return predmet;
	}
}
