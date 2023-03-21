package com.brianLester.FitnessTracker.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brianLester.FitnessTracker.models.Meal;
import com.brianLester.FitnessTracker.repos.MealRepo;

@Service
public class MealService {
	
	@Autowired
	MealRepo mRepo;
	
	
	public Meal createOrUpdate(Meal m) {
		return mRepo.save(m);
	}
	
	public Meal FindById(Long id) {
		Optional <Meal> meal = mRepo.findById(id);
		if(meal.isPresent()) {
			return meal.get();
		}
		else {
			return null;
		}
	}
	

	public List <Meal> FindallByCreatedAt(Date date){
		List <Meal> meals = mRepo.findAll();
		List <Meal> mealsForDay = new ArrayList<Meal>();
		 SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i = 0; i<=meals.size()-1; i++) {
			String date1 = sdformat.format(date);
			String date2 = sdformat.format(meals.get(i).getCreatedAt());
			if (date1.compareTo(date2) !=0) {
				mealsForDay.add(meals.get(i));
			}
			
		
			
		}
		return mealsForDay;
	}

	public void deleteMeal(Meal m) {
		mRepo.deleteById(m.getId());
		
	}

}
