package com.brianLester.FitnessTracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brianLester.FitnessTracker.models.Ingredient;
import com.brianLester.FitnessTracker.models.Meal;
import com.brianLester.FitnessTracker.repos.IngredientRepo;


@Service
public class IngredientService {
	
	@Autowired 
	IngredientRepo iRepo;
	
	
	public Ingredient FindByName(String name) {
		Optional <Ingredient> oIngredient = iRepo.findByIngredientName(name);
		if(oIngredient.isPresent()) {
			return oIngredient.get();
		}
		else {
			Ingredient newI = new Ingredient();
			newI.setIngredientName(name);
					
			return iRepo.save(newI);
		}
	}
	
	public List <Ingredient> allIngredients(String ingredients ,Meal meal) {
	String[] test = ingredients.split("[\\s,]+");
	List<Ingredient> listToReturn = new ArrayList<Ingredient>();
	for(int i = 0; i <= test.length-1; i++) {
		Ingredient j = new Ingredient();
		j.setIngredientName(test[i]);
		j.setMeal(meal);
		listToReturn.add(j);
		
		iRepo.save(j);
		
		
	}
	return listToReturn;
	}
}
	


