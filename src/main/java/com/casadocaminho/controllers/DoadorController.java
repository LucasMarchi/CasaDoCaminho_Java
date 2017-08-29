package com.casadocaminho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casadocaminho.models.Doador;
import com.casadocaminho.repositories.DoadorRepository;

@Controller
@RequestMapping("/doador")
public class DoadorController {
	
	@Autowired
	private DoadorRepository doadorRepository;
	
	@RequestMapping("/form")
	public String form() {
		return "/doador/form_doador";
	}
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Doador doador) {
		doadorRepository.save(doador);
		return "ok";
	}

}
