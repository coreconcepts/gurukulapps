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
		ValidationUtils
				.rejectIfEmpty(errors, "recipeName", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "genre", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "daysGoodFor",
				"event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "weight", "event.field.empty");
		ValidationUtils
				.rejectIfEmpty(errors, "temprature", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "process", "event.field.empty");

	}
}
/*
 * recipeName genre daysGoodFor weight temprature process
 */
