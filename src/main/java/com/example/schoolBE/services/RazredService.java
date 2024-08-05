package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.Razred1Entity;
import com.example.schoolBE.repositories.RazredRepository;

@Service
public class RazredService {
	@Autowired
	private RazredRepository razredRepo;
	
	public String getRazredById(int razredId) throws NotFoundException{
		Razred1Entity razred = razredRepo.findById(razredId);
		if (razred != null) {
            return "Razred je uspesno pronadjen";
        } else {
            throw new NotFoundException();
        }
	}
	public String addRazred(String imeRazreda) {
		Razred1Entity razred = new Razred1Entity();
		razred.setImeRazreda(imeRazreda);
		razredRepo.save(razred);
		return "Novi Razred je dodat";
	}
	public String updateRazred(int razredId,String imeRazreda) throws NotFoundException {
		Razred1Entity razred = razredRepo.findById(razredId);
		if(razred != null) {
			razred.setImeRazreda(imeRazreda);
			razredRepo.save(razred);
			return "Razred je uspesno azuriran!";
		}else{
			throw new NotFoundException();
		}
	}
	
}
