package com.midhun.crudoperations.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;



@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Movie name should not be null")
	private String name;
	@Positive(message = "Budget should not be negative")
	private int totalBudget;
	@PositiveOrZero(message = "Rating cannot be negative")
	private double rating;
	
	public Movie() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTotalBudget() {
		return totalBudget;
	}


	public double getRating() {
		return rating;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setTotalBudget(int totalBudget) {
		this.totalBudget = totalBudget;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
