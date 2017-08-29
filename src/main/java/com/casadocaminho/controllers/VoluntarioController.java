package com.casadocaminho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casadocaminho.models.Voluntario;
import com.casadocaminho.repositories.VoluntarioRepository;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@RequestMapping("/form")
	public String form() {
		return "/voluntario/form_voluntario";
	}
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
		return "/voluntario/lista_voluntarios";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("voluntarios", voluntarioRepository.findAll());
		return "/voluntario/lista_voluntarios";
	}
	
}
