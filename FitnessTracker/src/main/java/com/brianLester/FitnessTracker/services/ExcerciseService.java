package com.brianLester.FitnessTracker.services;

import java.util.List;
import java.util.Optional;

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

	public Excercise FindById(Long id) {
		Optional <Excercise> e = eRepo.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		else {
			return null;
		}
	}
	public void deleteExcercise(Excercise e) {
		eRepo.deleteById(e.getId());
	}
	
	

}
