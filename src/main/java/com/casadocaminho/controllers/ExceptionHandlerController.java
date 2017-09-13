package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(Exception.class)
	public String trataExceptionGenerica(Exception ex) {
		
		logger.error("Ocorreu erro na aplicação: " + ex.getMessage());
		
		return "error";
		
	}

}
