package com.brianLester.FitnessTracker.models;

import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="meals")
public class Meal {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotEmpty(message="Recipe name is required!")
	    @Size(min=3, max=30, message="Recipe name must be between 3 and 30 characters")
	    private String RecipeName;
	    
	    @Min(value=1, message="Must contain atleast 1 calorie")
	    private int calories;
	    
	    @OneToMany(mappedBy="meal", fetch = FetchType.LAZY )
	    private List<Ingredient> ingredients;
	    
	    
	    @Size(min=3, message="Description must be atleast 3 Characters")
	    private String description;
	    
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;

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

		public List<Ingredient> getIngredients() {
			return ingredients;
		}

		public void setIngredients(List<Ingredient> ingredients) {
			this.ingredients = ingredients;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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
}
