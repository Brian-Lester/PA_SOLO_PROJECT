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
import com.brianLester.FitnessTracker.services.MealService;
import com.brianLester.FitnessTracker.services.WorkoutService;

@Controller
public class WorkoutController {
	
	@Autowired
	WorkoutService wServ;
	
	@Autowired
	ExcerciseService eServ;
	
	@Autowired
	MealService mServ;
	
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
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
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
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		Workout cWorkout = wServ.FindById(id);
		cWorkout.setStart(System.currentTimeMillis());
		wServ.createOrUpdateUser(cWorkout);
		model.addAttribute("newWorkout", cWorkout);
		model.addAttribute("newExcercise", new Excercise());
		return "newWorkout.jsp";
	}
	@GetMapping("/end/time/{id}")
	public String endTime(HttpSession session, Model model,@PathVariable("id")Long id) {
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		Workout cWorkout = wServ.FindById(id);
		cWorkout.setEnd(System.currentTimeMillis());
		cWorkout.setTotalTime(TimeUnit.MILLISECONDS.toMinutes((cWorkout.getEnd() - cWorkout.getStart())));
		wServ.createOrUpdateUser(cWorkout);
		return "redirect:/home";
	}
	
	@GetMapping("/view/day/{id}")
	public String ViewDay(HttpSession session, Model model, @PathVariable("id") Long id) {
	if(session.getAttribute("user")== null) {
		return "redirect:/";
	}
	Workout workout =wServ.FindById(id);
	model.addAttribute("workout", workout);
	model.addAttribute("meals", mServ.FindallByCreatedAt(workout.getCreatedAt()));
	
	return "viewDay.jsp";
	}
	
	@GetMapping("/delete/wokrout/{id}")
	public String deleteWorkout(HttpSession session, @PathVariable("id") Long id) {
		if(session.getAttribute("user")== null) {
			return "redirect:/";
		}
		Workout w = wServ.FindById(id);
		User u = (User) session.getAttribute("user");
		if( u.getId()!= w.getUser().getId()) {
			return "redirect:/";
		}
		wServ.deleteWorkout(w);
		return"redirect:/home";
		
	}
	
	@GetMapping("/edit/wokrout/{id}")
	public String editWorkout(HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("user")== null) {
			return "redirect:/";
		}
		Workout w = wServ.FindById(id);
		User u = (User) session.getAttribute("user");
		if( u.getId()!= w.getUser().getId()) {
			return "redirect:/";
		}
		model.addAttribute("newWorkout", w);
		model.addAttribute("newExcercise", new Excercise());
		return"editWorkout.jsp";
		
	}
	@PostMapping("/edit/excercise")
	public String editWorkout(@Valid @ModelAttribute("newExcercise") Excercise newExcercise,BindingResult result,HttpSession session, Model model) {
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		if (result.hasErrors()) {
			Workout cWorkout = wServ.FindById(newExcercise.getWorkout().getId());
			model.addAttribute("newWorkout",cWorkout);
			return "editWorkout.jsp";
		}
		else {
			Workout cWorkout = wServ.FindById(newExcercise.getWorkout().getId());
			eServ.createOrUpdate(newExcercise);
			model.addAttribute("newWorkout",cWorkout);
			model.addAttribute("newExcercise", new Excercise());
			return "editWorkout.jsp";
		}
		}
			
	@GetMapping("/delete/excercise/{id}")
	public String deleteExcercise(HttpSession session, @PathVariable("id") Long id) {
		if(session.getAttribute("user")== null) {
			return "redirect:/";
		}
		Excercise e = eServ.FindById(id);
		User u = (User) session.getAttribute("user");
		if( u.getId()!= e.getWorkout().getUser().getId()) {
			return "redirect:/";
		}
		eServ.deleteExcercise(e);
		return"redirect:/edit/wokrout/" + e.getWorkout().getId();
		
		
	
	}
}
