package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.schoolBE.entities.IzostanciEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.IzostanciRepository;

@Service
public class IzostanciService {
	@Autowired
	private IzostanciRepository izostanciRepo;
	
	public Iterable<IzostanciEntity> getAll(){
		return izostanciRepo.findAll();
	}
	
	public IzostanciEntity getIzostanakById(int izostanak_id) throws NotFoundException{
		IzostanciEntity izostanak = izostanciRepo.findById(izostanak_id);
		if (izostanak != null) {
			return izostanak;
		} else {
			throw new NotFoundException();
		}
	}
	
	public IzostanciEntity addIzostanak(@RequestParam String opravdani,@RequestParam String neopravdani,
										@RequestParam String datumIzostanka,@RequestParam UcenikEntity ucenik) {
		
		
		IzostanciEntity izostanak = new IzostanciEntity();
		izostanak.setOpravdaniIzostanci(opravdani);
		izostanak.setNeopravdaniIzostanci(neopravdani);
		izostanak.setUcenik(ucenik);
		izostanak.setDatumIzostanka(datumIzostanka);
		izostanciRepo.save(izostanak);
		return izostanak;
	}
	
	public IzostanciEntity updateIzostanak(int id,String opravdani,String neopravdani,String datumIzostanka)throws NotFoundException{
		IzostanciEntity izostanak = izostanciRepo.findById(id);
		
		if(izostanak != null) {
			izostanak.setDatumIzostanka(datumIzostanka);
			izostanak.setNeopravdaniIzostanci(neopravdani);
			izostanak.setOpravdaniIzostanci(opravdani);
			izostanciRepo.save(izostanak);
			return izostanak;
		}else {
			throw new NotFoundException();
		}
	}
	
}
