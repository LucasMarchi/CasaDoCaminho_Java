package com.casadocaminho.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.casadocaminho.models.Voluntario;

public class VoluntarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Voluntario.class.isAssignableFrom(clazz);
	}

	@Override
	 public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "telefone", "field.required");
		
    }

}
