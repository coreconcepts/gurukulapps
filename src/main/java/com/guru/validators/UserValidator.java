package com.guru.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.guru.model.User;

@Component
public class UserValidator implements Validator {
	
	private final static String EMPLOYEES_NUMBER = "emplNumber";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		System.out.println("In User Validator");
		ValidationUtils.rejectIfEmpty(errors, "login", "user.login.empty");
		ValidationUtils.rejectIfEmpty(errors, "role", "user.role.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
		
		
	}

}
