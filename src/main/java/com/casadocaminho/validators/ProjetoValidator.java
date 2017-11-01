package com.casadocaminho.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.casadocaminho.models.Projeto;

public class ProjetoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Projeto.class.isAssignableFrom(clazz);
	}

	@Override
	 public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataInicio", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataTermino", "field.required");
		
		Projeto projeto = (Projeto) target;
		if (projeto.getDataInicio() != null && projeto.getDataTermino() != null && projeto.getDataTermino().isBefore(projeto.getDataInicio())) {
            errors.rejectValue("dataTermino", "field.data.maior");
        }
    }

}
