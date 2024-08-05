package com.example.schoolBE.services;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.schoolBE.entities.KorisnikEntity;

public interface KorisnikService {
	Iterable<KorisnikEntity> getAllUsers();
	Optional<KorisnikEntity> getUserById(int idKorisnika) throws NotFoundException;
	
	public KorisnikEntity addKorisnik(String korisnickoIme,String sifra,String uloga);
	String deleteUserById(int idKorisnika) throws NotFoundException;
	String updateUser(String izmenjenoKorisnickoIme,String izmenjenaSifraKorisnika,String izmenjenaUloga) throws NotFoundException;
}
