package com.brianLester.FitnessTracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brianLester.FitnessTracker.models.Workout;
import com.brianLester.FitnessTracker.repos.WorkoutRepo;

@Service
public class WorkoutService {
	@Autowired
	WorkoutRepo wRepo;
	
	public Workout createOrUpdateUser(Workout w) {
		return wRepo.save(w);
	}
	public Workout FindById(Long id) {
		
		 Optional<Workout> optionalWorkout = wRepo.findById(id);
	        if(optionalWorkout.isPresent()) {
	            return optionalWorkout.get();
	        } else {
	            return null;
	        }
	}
	
	public List<Workout> FindAll(){
		List<Workout> workouts = wRepo.findAll();
		List <Workout> allWorkouts = new ArrayList<Workout>();
		for(int i=0; i<workouts.size(); i++) {
			if(workouts.get(i).getExcercises().size() < 1 ) {
				wRepo.deleteById(workouts.get(i).getId());
			}
			else {
				allWorkouts.add(workouts.get(i));
			}
		}
		return allWorkouts;
	}
	
	public void deleteWorkout(Workout w) {
		wRepo.deleteById(w.getId());;
	}
}
