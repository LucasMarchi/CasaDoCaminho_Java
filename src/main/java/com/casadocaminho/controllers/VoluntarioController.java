package com.casadocaminho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView cadastrar(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
		return new ModelAndView("redirect:/voluntario/buscar");
	}
	
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/voluntario/lista_voluntarios");
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		return mv;
	}
	
}
