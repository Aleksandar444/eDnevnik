package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.OcenaEntity;


public interface OcenaRepository extends CrudRepository<OcenaEntity,Integer> {
	OcenaEntity findById(int ocenaId);
}
