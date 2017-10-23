package com.casadocaminho.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.casadocaminho.models.Projeto;

public class ProjetoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Projeto.class.isAssignableFrom(clazz);
	}

	@Override
	 public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "dataInicio", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataTermino", "field.required");
		
		Projeto projeto = (Projeto) target;
		if (projeto.getDataInicio() != null && projeto.getDataTermino() != null && projeto.getDataInicio().isAfter(projeto.getDataTermino())) {
            errors.rejectValue("datas", "field.required");
        }
    }

}
