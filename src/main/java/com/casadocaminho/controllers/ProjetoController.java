package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Filtro;
import com.casadocaminho.models.Projeto;
import com.casadocaminho.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjetoController.class);
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		logger.info("Retornando form_projeto");
		return new ModelAndView("projeto/form_projeto");
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Projeto projeto) {
		logger.info("Cadastrando projeto");
		projetoRepository.save(projeto);
		return new ModelAndView("redirect:projeto/listar");
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");
		mv.addObject("projetos", projetoRepository.findAll());
		logger.info("Retornando lista_projetos");
		return mv;
	}
	
	@RequestMapping(value="/listarPorFiltro", method=RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtro, @RequestParam Integer tipoFiltro) {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");
		
		if(tipoFiltro.equals(Filtro.NOME.getValor())) {
			mv.addObject("projetos", projetoRepository.findByNome(filtro));
		}
		
		logger.info("Retornando lista_projetos por filtro");
		return mv;
	}

}
