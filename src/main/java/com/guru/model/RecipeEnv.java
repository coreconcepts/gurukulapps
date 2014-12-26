package com.guru.model;

public class RecipeEnv {
	
	private Recipe recipe;
	private String envRPersonCount;
	
	public String getEnvRPersonCount() {
		return envRPersonCount;
	}

	public void setEnvRPersonCount(String rPersonCount) {
		this.envRPersonCount = rPersonCount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	private Component[] allComponents = new Component[10];

	public Component[] getAllComponents() {
		return allComponents;
	}

	public void setAllComponents(Component[] allComponents) {
		this.allComponents = allComponents;
	}
	
	
}
