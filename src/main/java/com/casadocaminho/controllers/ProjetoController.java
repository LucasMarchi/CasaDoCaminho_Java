package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Projeto;
import com.casadocaminho.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjetoController.class);
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@RequestMapping("/form")
	public String form() {
		logger.info("Retornando form_projeto");
		return "/projeto/form_projeto";
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Projeto projeto) {
		logger.info("Cadastrando projeto");
		projetoRepository.save(projeto);
		return new ModelAndView("redirect:/projeto/buscar");
	}
	
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/projeto/lista_projetos");
		mv.addObject("projetos", projetoRepository.findAll());
		logger.info("Retornando lista_projetos");
		return mv;
	}

}
