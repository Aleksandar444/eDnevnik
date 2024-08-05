package com.example.schoolBE.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.schoolBE.entities.KorisnikEntity;


@Repository
public interface KorisnikRepository extends CrudRepository<KorisnikEntity,Integer> {
	Optional<KorisnikEntity> findByKorisnickoIme(String korisnickoIme);
}
