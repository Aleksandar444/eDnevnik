package com.example.schoolBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.OcenaEntity;
import com.example.schoolBE.entities.PredmetEntity;
import com.example.schoolBE.entities.UcenikEntity;
import com.example.schoolBE.repositories.NastavnikRepository;
import com.example.schoolBE.repositories.OcenaRepository;
import com.example.schoolBE.repositories.PredmetRepository;
import com.example.schoolBE.repositories.UcenikRepository;

@Service
public class OcenaService {
	
	@Autowired
    private OcenaRepository ocenaRepo;

    @Autowired
    private UcenikRepository ucenikRepo;
    
    @Autowired
    private NastavnikRepository nastavnikRepo;
    
    @Autowired
    private PredmetRepository predmetRepo;
    
    public Iterable<OcenaEntity> getAll(){
    	return ocenaRepo.findAll();
    }
    
    public String getOcenaById(int ocenaId) throws NotFoundException{
    	OcenaEntity ocena = ocenaRepo.findById(ocenaId);
    	if (ocena != null) {
    		return "Ocena sa trazenim id-jem : " + ocenaId;
    	}else {
    		throw new NotFoundException();
    	}
    	
    }
    
    //dodavanje nove ocene u bazu
    public String addOcena(int ocena,int zakljucenaOcena,
    						int prvoPolugodiste,int drugoPolugodiste, 
    						int vladanje , int usmeni,
    						int pismeni,int nastavnikId,int ucenikId,int predmetId) {
    	
    	if (ocena < 1 || ocena >5) {
    		return "Ocena mora biti izmedju brojeva 1 i 5";
    	}else {
    		NastavnikEntity nastavnik = nastavnikRepo.findById(nastavnikId);
    		UcenikEntity ucenik = ucenikRepo.findById(ucenikId);
    		PredmetEntity predmet = predmetRepo.findById(predmetId);
    		
    		OcenaEntity oc = new OcenaEntity();
    		oc.setOcena(ocena);
    		oc.setPrvoPolugodiste(prvoPolugodiste);
    		oc.setDrugoPolugodiste(drugoPolugodiste);
    		oc.setVladanje(vladanje);
    		oc.setZakljucnaOcena(zakljucenaOcena);
    		oc.setUsmeni(usmeni);
    		oc.setPismeni(pismeni);
    		oc.setPredmet(predmet);
    		oc.setNastavnik(nastavnik);
    		oc.setUcenik(ucenik);
    		ocenaRepo.save(oc);
    	}
    	
    	return "Nova ocena je dodata!";
    	
    }
    
    //update ocene 
    public String updateOcena(int ocenaId,int zakljucenaOcena,
    						int prvoPolugodiste,int drugoPolugodiste, 
    						int vladanje , int usmeni,
    						int pismeni,int nastavnikId,int ucenikId,int predmetId) throws NotFoundException {
    	
    	OcenaEntity oc = new OcenaEntity();
    	if (oc != null) {
    		oc.setOcena(ocenaId);
    		oc.setPrvoPolugodiste(prvoPolugodiste);
    		oc.setDrugoPolugodiste(drugoPolugodiste);
    		oc.setPismeni(pismeni);
    		oc.setUsmeni(usmeni);
    		oc.setZakljucnaOcena(zakljucenaOcena);
    		oc.setVladanje(vladanje);
    		ocenaRepo.save(oc);
    		return "Ocena je azurirana!";
    	}else {
    		throw new NotFoundException();
    	}
    }
    
    //brisanje ocene
    public String deleteOcena(int ocenaId) throws NotFoundException {
    	OcenaEntity oc = new OcenaEntity();
    	if(oc !=null) {
    		ocenaRepo.delete(oc);
    		return "Ocena je obrisana!";
    	}else {
    		throw new NotFoundException();
    	}
    }
	
}
