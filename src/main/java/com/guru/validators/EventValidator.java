package com.guru.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.guru.model.Event;

@Component
public class EventValidator implements Validator {
	
	private final static String EMPLOYEES_NUMBER = "emplNumber";

	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Event event = (Event) target;
		System.out.println("In Event Validator");
		ValidationUtils.rejectIfEmpty(errors, "eventDetails", "event.eventDetails.empty");
		
		
	}

}
