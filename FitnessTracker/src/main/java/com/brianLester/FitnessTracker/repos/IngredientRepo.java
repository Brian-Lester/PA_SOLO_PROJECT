package com.brianLester.FitnessTracker.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brianLester.FitnessTracker.models.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

	List<Ingredient> findAll();
}
