package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.Razred1Entity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.entities.UcenikRazred;
import com.example.schoolBE.repositories.RazredRepository;
import com.example.schoolBE.repositories.UcenikRazredRepository;
import com.example.schoolBE.repositories.UcenikRepository;

@Service
public class UcenikRazredService {
	@Autowired
	private UcenikRepository ucenikRepo;
	@Autowired RazredRepository razredRepo;
	
	@Autowired
	private UcenikRazredRepository ucenikRazRepo;
	
	public void dodajUcenikRazred(Integer ucenikId,Integer razredId) {
		UcenikEntity ucenik = ucenikRepo.findById(ucenikId).orElseThrow();
		Razred1Entity razred = razredRepo.findById(razredId).orElseThrow();
		
		UcenikRazred veza = new UcenikRazred();
		veza.setRazred(razred);
		veza.setUcenik(ucenik);
		
		ucenikRazRepo.save(veza);
	}

}
