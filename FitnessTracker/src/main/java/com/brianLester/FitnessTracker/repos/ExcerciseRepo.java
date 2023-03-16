package com.brianLester.FitnessTracker.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brianLester.FitnessTracker.models.Excercise;

public interface ExcerciseRepo extends CrudRepository<Excercise, Long> {
	
	List <Excercise> findAll();
	
	Optional <Excercise> findByexcerciseName(String name);

}
