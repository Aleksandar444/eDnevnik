package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.Razred1Entity;

public interface RazredRepository extends CrudRepository<Razred1Entity, Integer> {
	Razred1Entity findById(int razredId);
	
}
