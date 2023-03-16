package com.brianLester.FitnessTracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brianLester.FitnessTracker.models.Excercise;
import com.brianLester.FitnessTracker.repos.ExcerciseRepo;

@Service
public class ExcerciseService {
	
	@Autowired
	ExcerciseRepo eRepo;
	
	 public List<Excercise> allExcercises(){
		return eRepo.findAll();
	}
	
	 public Excercise createOrUpdate(Excercise e) {
		 return eRepo.save(e);
	 }
	

}
