package com.example.schoolBE.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.schoolBE.entities.*;
public interface PredmetRepository extends CrudRepository<PredmetEntity,Integer>{
	PredmetEntity findById(int id);
}
