package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.UcenikEntity;

public interface UcenikRepository extends CrudRepository<UcenikEntity,Integer>{
	UcenikEntity findById(int ucenikId);
}
