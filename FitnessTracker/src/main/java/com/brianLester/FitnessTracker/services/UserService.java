package com.brianLester.FitnessTracker.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.brianLester.FitnessTracker.models.LoginUser;
import com.brianLester.FitnessTracker.models.User;
import com.brianLester.FitnessTracker.repos.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public UserService ()  {};
	
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	public User createOrUpdateUser(User u) {
		return userRepo.save(u);
	}
	 public Optional<User> findById(Long id) {
	    	return userRepo.findById(id);
	    }
	 
	 public void deleteById(Long id) {
		 userRepo.deleteById(id);
	 }
	 
	 
	 public User register(User newUser, BindingResult result) {
	      
	      if (result.hasErrors()) {
	    	  return null;
	      }
	      Optional <User> u = userRepo.findByEmail(newUser.getEmail());
	      
	      if(u.isPresent()) {
	    	  result.rejectValue("email", "email", "Email already in use please choose another or log in!");
	    	  return null;
	 }
	      if(!newUser.getPassword().equals(newUser.getConfirm())) {
	    	  result.rejectValue("password", "password", "passwords dont match please try again");
	    	  return null;
	      }
	      String hashPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
	      newUser.setPassword(hashPW);
	      return userRepo.save(newUser);
	      
	 }
	 
	    public Optional<User> login(LoginUser newLoginObject, BindingResult result) {
		      if (result.hasErrors()) {
		    	  return null;
		      }
		      Optional<User> user = userRepo.findByEmail(newLoginObject.getEmail());
		      
		      if(!user.isPresent()) {
		    	  result.rejectValue("email", "email", "Invalid credientials!");
		    	  return null;
		      }
		      if(!BCrypt.checkpw(newLoginObject.getPassword(), user.get().getPassword())) {
		    	    result.rejectValue("password", "password", "Invalid credientials!");
		    	    return null;
		    	}
		    		
		    		return user;
		    
		      }
	    }


