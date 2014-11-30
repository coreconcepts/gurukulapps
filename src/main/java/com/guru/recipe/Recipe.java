package com.guru.recipe;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Recipe {

	@Size(min=1, message = "Recipe Name is required.")
	private String recipeName;
	
	@NotBlank(message = "Notes are required.")
	private String notes;
	
	
	@NotBlank(message = "Components are required.")
	private Component[] componentsList;
	
	
	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Component[] getComponentsList() {
		return componentsList;
	}


	public void setComponentsList(Component[] componentsList) {
		this.componentsList = componentsList;
	}


	
	
}