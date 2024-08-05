package com.example.schoolBE.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.NastavnikEntity;
import com.example.schoolBE.entities.PredajePredmetEntity;

public interface PredajePredmetRepositoy extends CrudRepository<PredajePredmetEntity,Integer> {

}
