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
@Table(name="ingredients")
public class Ingredient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Recipe name is required!")
    @Size(min=3, max=30, message="Recipe name must be between 3 and 30 characters")
    private String RecipeName;
    
    @Min(value=1, message="Must contain atleast 1 calorie")
    private int calories;
    
     @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meal_id")
    private Meal meal;
    
    public Ingredient() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipeName() {
		return RecipeName;
	}

	public void setRecipeName(String recipeName) {
		RecipeName = recipeName;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
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
	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	};
    
    

}
