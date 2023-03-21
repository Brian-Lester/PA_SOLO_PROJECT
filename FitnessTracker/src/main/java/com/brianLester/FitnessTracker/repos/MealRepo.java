package com.brianLester.FitnessTracker.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brianLester.FitnessTracker.models.Meal;

public interface MealRepo extends CrudRepository<Meal, Long> {
	
	List<Meal> findAll();
	
//	List<Meal> findAll(Date date);

}
