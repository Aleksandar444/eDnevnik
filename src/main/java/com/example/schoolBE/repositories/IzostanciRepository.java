package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.IzostanciEntity;

public interface IzostanciRepository extends CrudRepository<IzostanciEntity,Integer> {
	IzostanciEntity findById(int izostanakId);
}
