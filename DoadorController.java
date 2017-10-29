package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Doador;
import com.casadocaminho.repositories.ProjetoRepository;
import com.casadocaminho.repositories.DoadorRepository;

@Controller
@RequestMapping("/doador")
public class DoadorController {

	private static final Logger logger = LoggerFactory.getLogger(DoadorController.class);

	@Autowired
	private DoadorRepository doadorRepository;

	@RequestMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("doador/form_doador");
	}

	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Doador doador) {
		doadorRepository.save(doador);
		return new ModelAndView("redirect:listar");
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("doador/lista_doadores");
		mv.addObject("doadores", doadorRepository.findAll());
		logger.info("Retornando lista_doadores por filtro");
		return mv;
	}

	@RequestMapping(value = "/listarDoadores", method = RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtro) {
		ModelAndView mv = new ModelAndView("doador/lista_doadores");
		mv.addObject("doadores", doadorRepository.findByDoadorNome(filtro));
		logger.info("Retornando lista_doadores por filtro");
		return mv;
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("doador/form_doador");
		mv.addObject("doador", doadorRepository.findById(id));
		return mv;
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Integer id) {
		doadorRepository.delete(id);
		ModelAndView mv = new ModelAndView("doador/listar");
		return mv;
	}

}
