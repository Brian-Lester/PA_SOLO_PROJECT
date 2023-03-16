package com.brianLester.FitnessTracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="excercises")
public class Excercise {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Excercise name is required!")
    @Size(min=3, max=30, message="Excercise name must be between 3 and 30 characters")
    private String excerciseName;
    
   @Column(updatable=false)
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date createdAt;
   
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date updatedAt;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="workout_id")
   private Workout workout;
   
   @Min(value =1, message= "Must be at least 1 rep!")
   private int rep;
   
   @Min(value =1, message= "Must be at least 1 Pound!")
   private double weight;
   
   @Min(value=1, message="Must be at least 1 Set!")
   private int sets;
   
   private String excerciseType;
   
   public Excercise() {}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getExcerciseName() {
	return excerciseName;
}

public void setExcerciseName(String excerciseName) {
	this.excerciseName = excerciseName;
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

public Workout getWorkout() {
	return workout;
}

public void setWorkout(Workout workout) {
	this.workout = workout;
}

public int getRep() {
	return rep;
}

public void setRep(int rep) {
	this.rep = rep;
}

public double getWeight() {
	return weight;
}

public void setWeight(double weight) {
	this.weight = weight;
}

public int getSets() {
	return sets;
}

public void setSets(int sets) {
	this.sets = sets;
}

public String getExcerciseType() {
	return excerciseType;
}

public void setExcerciseType(String excerciseType) {
	this.excerciseType = excerciseType;
};
}
