package com.brianLester.FitnessTracker.controllers;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.brianLester.FitnessTracker.models.Excercise;
import com.brianLester.FitnessTracker.models.User;
import com.brianLester.FitnessTracker.models.Workout;
import com.brianLester.FitnessTracker.services.ExcerciseService;
import com.brianLester.FitnessTracker.services.WorkoutService;

@Controller
public class WorkoutController {
	
	@Autowired
	WorkoutService wServ;
	
	@Autowired
	ExcerciseService eServ;
	
	@GetMapping("/new/workout")
	public String newWorkout(Model model,HttpSession session) {
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		Workout testing = new Workout();
		testing.setUser((User) session.getAttribute("user"));
		wServ.createOrUpdateUser(testing);
		model.addAttribute("newWorkout", testing);
		model.addAttribute("newExcercise", new Excercise());
		model.addAttribute("user", session.getAttribute("user"));
		return "newWorkout.jsp";
	}
	
	@PostMapping("/new/excercise")
	public String newExcercise(@Valid @ModelAttribute("newExcercise") Excercise newExcercise,BindingResult result,HttpSession session, Model model) {
		if (result.hasErrors()) {
			Workout cWorkout = wServ.FindById(newExcercise.getWorkout().getId());
			model.addAttribute("newWorkout",cWorkout);
			return "newWorkout.jsp";
		}
		else {
			Workout cWorkout = wServ.FindById(newExcercise.getWorkout().getId());
			eServ.createOrUpdate(newExcercise);
			model.addAttribute("newWorkout",cWorkout);
			model.addAttribute("newExcercise", new Excercise());
			return "newWorkout.jsp";
			
			
		
	}
	}
	
	@GetMapping("/start/time/{id}")
	public String startTime(HttpSession session, Model model,@PathVariable("id")Long id) {
		Workout cWorkout = wServ.FindById(id);
		cWorkout.setStart(System.currentTimeMillis());
		wServ.createOrUpdateUser(cWorkout);
		model.addAttribute("newWorkout", cWorkout);
		model.addAttribute("newExcercise", new Excercise());
		return "newWorkout.jsp";
	}
	@GetMapping("/end/time/{id}")
	public String endTime(HttpSession session, Model model,@PathVariable("id")Long id) {
		Workout cWorkout = wServ.FindById(id);
		cWorkout.setEnd(System.currentTimeMillis());
		cWorkout.setTotalTime(TimeUnit.MILLISECONDS.toMinutes((cWorkout.getEnd() - cWorkout.getStart())));
		wServ.createOrUpdateUser(cWorkout);
		model.addAttribute("newWorkout", cWorkout);
		model.addAttribute("newExcercise", new Excercise());
		return "redirect:/home";
	}
}
