package com.brianLester.FitnessTracker.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brianLester.FitnessTracker.models.Workout;

public interface WorkoutRepo extends CrudRepository<Workout,Long> {
	
	List <Workout> findAll();
}
