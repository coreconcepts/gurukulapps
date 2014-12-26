package com.guru.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.guru.model.Recipe;

@Component
public class RecipeValidator implements Validator {

	private final static String EMPLOYEES_NUMBER = "emplNumber";

	@Override
	public boolean supports(Class<?> clazz) {
		return Recipe.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Recipe recipe = (Recipe) target;
		System.out.println("In Event Validator");
		
		//errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		try{
			Integer.parseInt(recipe.getDaysGoodFor());
		} catch(NumberFormatException nfe) {
			errors.rejectValue("daysGoodFor", "recipe.non.numeric");
		}
		
		try{
			Integer.parseInt(recipe.getWeight());
		} catch(NumberFormatException nfe) {
			errors.rejectValue("weight", "recipe.non.numeric");
		}
		for(com.guru.model.Component  component:  recipe.getComponents()){
			try{	
				Integer.parseInt(component.getQuantity());
			} catch(NumberFormatException nfe) {
				errors.rejectValue("components", "recipe.non.numeric");
				break;
			}
		}
		
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "recipeName", "event.field.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "event.field.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "daysGoodFor",
				"event.field.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "event.field.empty");
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "temprature", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "process", "event.field.empty");

	}
}
/*
 * recipeName genre daysGoodFor weight temprature process
 */
