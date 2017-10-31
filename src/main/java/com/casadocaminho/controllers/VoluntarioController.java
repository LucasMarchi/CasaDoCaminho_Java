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

import com.casadocaminho.models.Voluntario;
import com.casadocaminho.repositories.ProjetoRepository;
import com.casadocaminho.repositories.VoluntarioRepository;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

	private static final Logger logger = LoggerFactory.getLogger(VoluntarioController.class);

	@Autowired
	private VoluntarioRepository voluntarioRepository;

	@Autowired
	private ProjetoRepository projetoRepository;

	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("voluntario/form_voluntario");
		mv.addObject("listaProjetos", projetoRepository.findAll());
		return mv;
	}

	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
		return new ModelAndView("redirect:listar");
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		logger.info("Retornando lista_voluntarios por filtro");
		return mv;
	}

	@RequestMapping(value = "/listarPorProjeto", method = RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtroProjeto) {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
//		mv.addObject("voluntarios", voluntarioRepository.findByProjetoNome(filtroProjeto));
		logger.info("Retornando lista_voluntarios por filtro");
		return mv;
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("voluntario/form_voluntario");
		mv.addObject("voluntario", voluntarioRepository.findById(id));
		return mv;
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Integer id) {
		projetoRepository.deleteVoluntarioFromProjeto(id);
		voluntarioRepository.delete(id);
		ModelAndView mv = new ModelAndView("voluntario/listar");
		return mv;
	}

}
