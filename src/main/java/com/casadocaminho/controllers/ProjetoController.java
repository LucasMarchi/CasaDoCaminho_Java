package com.casadocaminho.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.casadocaminho.validators.ProjetoValidator;

@Controller
@RequestMapping("projeto")
public class ProjetoController {

	private static final Logger logger = LoggerFactory.getLogger(ProjetoController.class);

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProjetoValidator());
    }

	@RequestMapping("form")
	public ModelAndView form(Projeto projeto) {
		logger.info("Retornando form_projeto");
		return new ModelAndView("projeto/form_projeto");
	}

	@RequestMapping("cadastrar")
	@CacheEvict(value = "listarProjetos", allEntries = true)
	public ModelAndView cadastrar(@Valid @ModelAttribute Projeto projeto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return new ModelAndView("projeto/form_projeto");
        }
		
		logger.info("Cadastrando projeto");
		projetoRepository.save(projeto);
		
		return new ModelAndView("redirect:listar");
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	@Cacheable("listarProjetos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");
		mv.addObject("projetos", projetoRepository.findAll());
		logger.info("Retornando lista_projetos");
		return mv;
	}

	@RequestMapping(value = "listarPorFiltro", method = RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtro, @RequestParam Integer tipoFiltro) {
		ModelAndView mv = new ModelAndView("projeto/lista_projetos");

		if (tipoFiltro.equals(Filtro.NOME.getValor())) {
			mv.addObject("projetos", projetoRepository.findByNome(filtro));
		}

		logger.info("Retornando lista_projetos por filtro");
		return mv;
	}
	
	@RequestMapping("{id}/visualisar/voluntarios")
	public ModelAndView visualisarProjeto(@PathVariable("id") Integer idProjeto) {
		//Mostra os voluntarios registrados no projeto
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("visualizacao", true);
		mv.addObject("projetoId", idProjeto);
		Projeto projeto = projetoRepository.findById(idProjeto);
		mv.addObject("voluntarios", projeto.getVoluntarios());
		return mv;
	}

	@RequestMapping("{id}/adicionar/voluntarios")
	public ModelAndView visualisarVoluntarios(@PathVariable("id") Integer id) {
		//Busca voluntarios pra registrar no projeto
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("inclusao", true);
		mv.addObject("projetoId", id);
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		return mv;
	}
	
	@RequestMapping("{id}/excluir/voluntario")
	public ModelAndView excluirVoluntarios(@PathVariable("id") Integer id) {
		//Remove voluntario do projeto
		projetoRepository.deleteVoluntarioFromProjeto(id);
		ModelAndView mv = new ModelAndView("redirect:voluntario/listar");
		mv.addObject("inclusao", true);
		mv.addObject("projetoId", id);
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		return mv;
	}
	
	@RequestMapping("registrar/voluntario")
	public ResponseEntity<Object> adicionarVoluntario(@RequestParam Integer idProjeto, @RequestParam Integer idVoluntario) {
		//Registra voluntario ao projeto
		List<Voluntario> voluntarios = new ArrayList<>();
		
		Voluntario voluntario =  voluntarioRepository.findById(idVoluntario);
		voluntarios.add(voluntario);
		Projeto projeto = projetoRepository.findById(idProjeto);
//		projeto.setVoluntarios(voluntarios);
		
		projetoRepository.save(projeto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
