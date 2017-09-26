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
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.repositories.VoluntarioRepository;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(VoluntarioController.class);
	
	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("voluntario/form_voluntario");
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
		return new ModelAndView("redirect:voluntario/buscar");
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		logger.info("Retornando lista_voluntarios por filtro");
		return mv;
	}
	
	@RequestMapping(value="/listarPorFiltro", method=RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtro, @RequestParam Integer tipoFiltro) {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		
		if(tipoFiltro.equals(Filtro.NOME.getValor())) {
			mv.addObject("voluntarios", voluntarioRepository.findByNome(filtro));
		}

		logger.info("Retornando lista_voluntarios por filtro");
		return mv;
	}
	
}
