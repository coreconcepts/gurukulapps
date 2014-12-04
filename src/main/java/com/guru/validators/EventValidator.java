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
		ValidationUtils.rejectIfEmpty(errors, "eventName", "event.eventName.empty");
		ValidationUtils.rejectIfEmpty(errors, "eventDetails", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "eventStartTime", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "eventEndTime", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "eventSelfRating", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "lessons", "event.field.empty");
		ValidationUtils.rejectIfEmpty(errors, "eventLocation", "event.field.empty");
				
		
	}

}
