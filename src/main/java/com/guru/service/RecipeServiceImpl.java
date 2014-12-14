package com.guru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guru.exceptions.RecipeNotFound;
import com.guru.model.Component;
import com.guru.model.Recipe;
import com.guru.repository.RecipeRepositoryCustom;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	UserService uServ;
	
	@Resource
	private RecipeRepositoryCustom recipeRepository;

	@Override
	@Transactional
	public Recipe create(Recipe recipe) {
		
		for(Component comp: recipe.getComponents()){
			comp.setRecipe(recipe);
		}
		// Temporary arrangement.
		recipe.setAuthorLogin("1000");
		recipe.setCreateDate(new Date());
		return recipeRepository.save(recipe);
	}
	
	@Override
	@Transactional
	public Recipe findById(int id) {
		return recipeRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=RecipeNotFound.class)
	public Recipe delete(int id) throws RecipeNotFound {
		Recipe deletedRecipe = recipeRepository.findOne(id);
		
		if (deletedRecipe == null)
			throw new RecipeNotFound();
		
		recipeRepository.delete(deletedRecipe);
		return deletedRecipe;
	}

	@Override
	@Transactional
	public Iterable<Recipe> findAll() {
		
			return recipeRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=RecipeNotFound.class)
	public Recipe update(Recipe recipe) throws RecipeNotFound {
//		Recipe updatedRecipe = recipeRepository.findOne(recipe.getId());
//		
//		if (updatedRecipe == null)
//			throw new RecipeNotFound();
//		
//		updatedRecipe.setName(recipe.getName());
//		updatedRecipe.setEmplNumber(recipe.getEmplNumber());
//		return updatedRecipe;
		return null;
	}

	@Override
	public Iterable<Recipe> findByRecipeName(String recipeName) {
		return recipeRepository.findByRecipeName(recipeName);
	}

}
