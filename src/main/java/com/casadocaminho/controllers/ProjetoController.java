package com.casadocaminho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casadocaminho.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listarProjetos(Model model) {
		model.addAttribute("projetos", projetoRepository.findAll());
		return "projeto/lista_projetos";
	}

}
