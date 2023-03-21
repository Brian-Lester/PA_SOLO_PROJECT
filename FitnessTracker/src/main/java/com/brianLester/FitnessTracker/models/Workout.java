package com.brianLester.FitnessTracker.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "workouts")
public class Workout {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	 	@OneToMany(mappedBy="workout", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
	    private List<Excercise> excercises;
	 	
	 	@OneToMany(mappedBy="workout", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
	    private List<Meal> meals;
	    
	    
	    
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;
	    
	    private Long start ;
	    private Long end ;
	    
	    private Long totalTime ;
	    
	    
	    public Workout() {};
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public List<Excercise> getExcercises() {
			return excercises;
		}

		public void setExcercises(List<Excercise> excercises) {
			this.excercises = excercises;
		}
		
		public void addToExcercises(Excercise excercise) {
		excercise.setWorkout(this);
		this.excercises.add(excercise);
		}
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Date getCreatedAt() {
			return createdAt;
		}
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
		public Date getUpdatedAt() {
			return updatedAt;
		}
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }

		public Long getStart() {
			return start;
		}

		public void setStart(Long start) {
			this.start = start;
		}

		public Long getEnd() {
			return end;
		}

		public void setEnd(Long end) {
			this.end = end;
		}

		public Long getTotalTime() {
			return totalTime;
		}

		public void setTotalTime(Long totalTime) {
			this.totalTime = totalTime;
		}

		public List<Meal> getMeals() {
			return meals;
		}

		public void setMeals(List<Meal> meals) {
			this.meals = meals;
		}

	    
		
		
	    
	    
	    
	    
	    

}
