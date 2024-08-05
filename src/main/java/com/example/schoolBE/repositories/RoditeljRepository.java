package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.RoditeljEntity;

public interface RoditeljRepository extends CrudRepository<RoditeljEntity,Integer> {
	Iterable<RoditeljEntity> findAll();
	RoditeljEntity findById(int roditeljId);
	
}
