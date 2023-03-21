package com.brianLester.FitnessTracker.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.brianLester.FitnessTracker.models.Meal;
import com.brianLester.FitnessTracker.models.User;
import com.brianLester.FitnessTracker.models.Ingredient;
import com.brianLester.FitnessTracker.services.IngredientService;
import com.brianLester.FitnessTracker.services.MealService;
import com.brianLester.FitnessTracker.services.WorkoutService;

@Controller
public class MealController {
	
	@Autowired
	private MealService mServ;
	
	@Autowired
	private IngredientService iServ;
	
	@Autowired
	private WorkoutService wServ;
	
	
	@GetMapping("/new/meal")
	public String newMeal(HttpSession session, Model model) {
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		model.addAttribute("newMeal", new Meal());
		model.addAttribute("days", wServ.FindAll());
		return"newMeal.jsp";
	}
	
	@PostMapping("/new/meal")
	public String pNewMeal(@Valid @ModelAttribute("newMeal") Meal newMeal,BindingResult result, HttpSession session, Model model,@RequestParam("ingredient") String ingredient) {
		if(session.getAttribute("user")== null) {
    		return "redirect:/";
    	}
		if(result.hasErrors()) {
			model.addAttribute("days", wServ.FindAll());
			return "newMeal.jsp";
		}
		mServ.createOrUpdate(newMeal);
		newMeal.setIngredients(iServ.allIngredients(ingredient,newMeal));
		mServ.createOrUpdate(newMeal);
		return "redirect:/home";
	}
	
	@GetMapping("/delete/meal/{id}")
	public String deleteMeal(HttpSession session, @PathVariable("id") Long id) {
		if(session.getAttribute("user")== null) {
			return "redirect:/";
		}
		Meal m = mServ.FindById(id);
		User u = (User) session.getAttribute("user");
		if( u.getId()!= m.getUser().getId()) {
			return "redirect:/";
		}
		mServ.deleteMeal(m);
		return"redirect:/view/day/" + m.getWorkout().getId();

	}
	
	@GetMapping("/edit/meal/{id}")
	public String editMeal(HttpSession session, @PathVariable("id") Long id , Model model) {
		if(session.getAttribute("user")== null) {
			return "redirect:/";
		}
		Meal m = mServ.FindById(id);
		User u = (User) session.getAttribute("user");
		if( u.getId()!= m.getUser().getId()) {
			return "redirect:/";
		}
		model.addAttribute("newMeal", m);
		model.addAttribute("days", wServ.FindAll());
		return"editMeal.jsp";

	}
//	@PostMapping("/edit/meal/{id}")
//	public String editMeal(@Valid @ModelAttribute("newMeal") Meal newMeal,BindingResult result,HttpSession session, @PathVariable("id") Long id , Model model,@RequestParam("ingredient") String ingredient) {
//		if(session.getAttribute("user")== null) {
//			return "redirect:/";
//		}
//		Meal m = mServ.FindById(id);
//		if(result.hasErrors()) {
//			model.addAttribute("days", wServ.FindAll());
//			return "newMeal.jsp";
//		}
//		List <Ingredient> i = newMeal.getIngredients();
//		i.addAll(iServ.allIngredients(ingredient,newMeal));
//		newMeal.setIngredients(i);
//		mServ.createOrUpdate(m);
//		return"redirect:/edit/meal/" + m.getId();
//
//	}
}
