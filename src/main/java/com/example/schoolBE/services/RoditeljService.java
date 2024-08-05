package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.RoditeljEntity;
import com.example.schoolBE.repositories.RoditeljRepository;

@Service
public class RoditeljService {

	
	@Autowired
	private RoditeljRepository roditeljRepo;
	
	public Iterable<RoditeljEntity> getAll(){ // vracanje svih roditelja iz baze
		return roditeljRepo.findAll();
	}
	
	public String getRoditeljById(int roditeljId) throws NotFoundException { // vracanje roditelja po ID
		RoditeljEntity roditelj = roditeljRepo.findById(roditeljId);
		if(roditelj != null) {
			return "Roditelj je pronadjen!";
		}else {
			 throw new NotFoundException();
		}
	}
	
	public String addRoditelj(String imeRoditelja,String prezimeRoditelja,String emailRoditelja) { // dodavanje novog roditelja
		RoditeljEntity roditelj = new RoditeljEntity();
		roditelj.setImeRoditelja(imeRoditelja);
		roditelj.setPrezimeRoditelja(prezimeRoditelja);
		roditelj.setEmailRoditelja(emailRoditelja);
		roditeljRepo.save(roditelj);
		return "Novi roditelj je uspesno dodat!";
	}
	
	public String updateRoditelj(int roditeljId,String imeRoditelja,String prezimeRoditelja,String emailRoditelja) throws NotFoundException {
		RoditeljEntity roditelj = roditeljRepo.findById(roditeljId);
		if(roditelj != null) {
			roditelj.setImeRoditelja(imeRoditelja);
			roditelj.setPrezimeRoditelja(prezimeRoditelja);
			roditelj.setEmailRoditelja(emailRoditelja);
			roditeljRepo.save(roditelj);
			return "Roditelj je uspesno azuriran!";
		}else {
			 throw new NotFoundException();
		}
	}
	
	
}
