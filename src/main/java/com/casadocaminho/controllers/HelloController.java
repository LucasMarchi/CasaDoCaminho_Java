package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("hello")
	public String home() {
		logger.info("Retornando página home");
		return "hello";
	}

}
