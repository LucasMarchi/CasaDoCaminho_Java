package com.casadocaminho.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Filtro;
import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.repositories.ProjetoRepository;
import com.casadocaminho.repositories.VoluntarioRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

	private static final Logger logger = LoggerFactory.getLogger(ProjetoController.class);

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private VoluntarioRepository voluntarioRepository;

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

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");
		mv.addObject("projetos", projetoRepository.findAll());
		logger.info("Retornando lista_projetos");
		return mv;
	}

	@RequestMapping(value = "/listarPorFiltro", method = RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtro, @RequestParam Integer tipoFiltro) {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");

		if (tipoFiltro.equals(Filtro.NOME.getValor())) {
			mv.addObject("projetos", projetoRepository.findByNome(filtro));
		}

		logger.info("Retornando lista_projetos por filtro");
		return mv;
	}
	
	@RequestMapping("/visualisar/{id}")
	public ModelAndView visualisarProjeto(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("/projeto/inclusao_voluntario");
		mv.addObject("projetoId", id);
		mv.addObject("voluntarios", voluntarioRepository.findByProjetoId(id));
		return mv;
	}

	@RequestMapping("/visualisar/{id}/voluntario")
	public ModelAndView visualisarVoluntarios(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("/voluntario/lista_voluntarios");
		mv.addObject("inclusao", true);
		mv.addObject("projetoId", id);
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		return mv;
	}
	
	@RequestMapping("/adicionar/voluntario")
	public ResponseEntity<Object> adicionarVoluntario(@RequestParam Integer idProjeto, @RequestParam Integer idVoluntario) {
		List<Voluntario> voluntarios = new ArrayList<>();
		
		Voluntario voluntario =  voluntarioRepository.findById(idVoluntario);
		voluntarios.add(voluntario);
		Projeto projeto = projetoRepository.findById(idProjeto);
		projeto.setVoluntarios(voluntarios);
		
		projetoRepository.save(projeto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
