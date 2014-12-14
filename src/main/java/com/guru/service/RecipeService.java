package com.guru.service;

import java.util.List;

import com.guru.exceptions.RecipeNotFound;
import com.guru.model.Recipe;

public interface RecipeService {
	
	public Recipe create(Recipe recipe);
	public Recipe delete(int id) throws RecipeNotFound;
	public Iterable<Recipe> findAll();
	public Recipe update(Recipe recipe) throws RecipeNotFound;
	public Recipe findById(int id);
	public Iterable<Recipe> findByRecipeName(String recipeName);

}
