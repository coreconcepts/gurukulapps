package com.guru.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.model.Event;
import com.guru.model.Recipe;
@Repository
public interface RecipeRepositoryCustom extends CrudRepository<Recipe, Integer> {
	
	public Iterable<Recipe> findByRecipeName(String recipeName);
}
