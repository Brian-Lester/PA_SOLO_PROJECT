package com.brianLester.FitnessTracker.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brianLester.FitnessTracker.models.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

	List<Ingredient> findAll();
	
	Optional <Ingredient> findByIngredientName(String name);
}
