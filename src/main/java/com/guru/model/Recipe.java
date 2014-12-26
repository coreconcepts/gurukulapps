package com.guru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe  implements Serializable{

	@Id
	@GeneratedValue
	private int recipeId;

	private String recipeName;
	private String genre;
	private String daysGoodFor;
	private String weight;
	private String temprature;
	private String process;
	private Integer personCount;
	
	
	
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "recipe")
	private List<Component> components = new ArrayList<Component>();

	public int getId() {
		return recipeId;
	}

	public void setId(int id) {
		this.recipeId = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDaysGoodFor() {
		return daysGoodFor;
	}

	public void setDaysGoodFor(String daysGoodFor) {
		this.daysGoodFor = daysGoodFor;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTemprature() {
		return temprature;
	}

	public void setTemprature(String temprature) {
		this.temprature = temprature;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public void setAuthorLogin(String string) {
		// TODO Auto-generated method stub

	}

	public void setCreateDate(Date date) {
		// TODO Auto-generated method stub

	}

	public List<Component> getComponents() {
		return components;

	}

	public void setComponents(List<Component> components) {
		this.components = components;
		if (components != null) {
			for (Component comp : components) {
				comp.setRecipe(this);
			}
		}
	}

	public void addComponent(Component component) {
		this.components.add(component);
		component.setRecipe(this);
	}

	public void removeComponent(int rowId) {
		this.getComponents().remove(rowId);
	}

}