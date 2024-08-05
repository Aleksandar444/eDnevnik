package com.example.schoolBE.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.schoolBE.entities.RoditeljEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.UcenikRepository;

@Service
public class UcenikService {
	
	@Autowired
	private UcenikRepository ucenikRepo;
	private Map<Integer, UcenikEntity> students = new HashMap<>();
	
	public UcenikEntity addUcenik(String ime,String prezime,RoditeljEntity roditelj) {
		UcenikEntity ucenik = new UcenikEntity();
		ucenik.setIme(ime);
		ucenik.setPrezime(prezime);
		ucenik.setRoditelj(roditelj);
		ucenikRepo.save(ucenik);
		return ucenik;
	}
	
	public UcenikEntity updateUcenik(Integer ucenikId,String ime,String prezime,RoditeljEntity roditelj) {
		if(!ucenikRepo.existsById(ucenikId)) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ucenik not found");
		}
		UcenikEntity ucenik = ucenikRepo.findById(ucenikId).get();
		ucenik.setIme(ime);
		ucenik.setPrezime(prezime);
		ucenik.setRoditelj(roditelj);
		ucenikRepo.save(ucenik);
		return ucenik;
	}
	
	 public Optional<UcenikEntity> findById(int id) {
	        return Optional.ofNullable(ucenikRepo.findById(id));
	 }

}
