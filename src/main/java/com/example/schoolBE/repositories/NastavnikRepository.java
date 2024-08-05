package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.*;

public interface NastavnikRepository extends CrudRepository<NastavnikEntity,Integer>{
	NastavnikEntity findById(int id);
}
