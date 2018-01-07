package com.casadocaminho.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.repositories.ProjetoRepository;
import com.casadocaminho.repositories.VoluntarioRepository;
import com.casadocaminho.validators.VoluntarioValidator;

@Controller
@RequestMapping("voluntario")
public class VoluntarioController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VoluntarioController.class);

	@Autowired
	private VoluntarioRepository voluntarioRepository;

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(new VoluntarioValidator());
    }

	@RequestMapping("form")
	public ModelAndView form(Voluntario voluntario) {
		ModelAndView mv = new ModelAndView("voluntario/form_voluntario");
		mv.addObject("projetos", projetoRepository.findAll());
		LOGGER.info("Retornando form_voluntario");
		return mv;
	}

	@RequestMapping("cadastrar")
	@CacheEvict(value = "listarVoluntarios", allEntries = true)
	public ModelAndView cadastrar(@Valid @ModelAttribute Voluntario voluntario, BindingResult bindingResult) {
		ModelAndView mv;
		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("voluntario/form_voluntario"); 
			mv.addObject("projetos", projetoRepository.findAll());
			LOGGER.error("Dados do voluntario inválidos");
			LOGGER.info("Retornando form_voluntario");
			return mv;
		}
		voluntarioRepository.save(voluntario);
		List<Projeto> projetos = voluntario.getProjetos();
		if(!projetos.isEmpty()) adicionarEmProjetos(voluntario, projetos);
		LOGGER.info("Voluntario cadastrado com sucesso");
		mv = new ModelAndView("redirect:listar");
		LOGGER.info("Retornando lista_voluntarios");
		return mv;
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	@Cacheable("listarVoluntarios")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		LOGGER.info("Retornando lista_voluntarios");
		return mv;
	}

	@RequestMapping(value = "listarPorProjeto", method = RequestMethod.GET)
	public ModelAndView listarPorFiltro(@RequestParam String filtroProjeto) {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
//		mv.addObject("voluntarios", voluntarioRepository.findByProjetoNome(filtroProjeto));
		LOGGER.info("Retornando lista_voluntarios por filtro");
		return mv;
	}

	@RequestMapping("editar/{id}")
	@CacheEvict(value = "listarVoluntarios", allEntries = true)
	public ModelAndView editar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("voluntario/form_voluntario");
		mv.addObject("listaProjetos", projetoRepository.findAll());
		mv.addObject("voluntario", voluntarioRepository.findById(id));
		return mv;
	}

	@RequestMapping("excluir/{id}")
	@CacheEvict(value = "listarVoluntarios", allEntries = true)
	public ModelAndView excluir(@PathVariable("id") Integer id) {
		projetoRepository.deleteVoluntarioFromProjeto(id);
		voluntarioRepository.delete(id);
		LOGGER.info("Voluntario deletado com sucesso");
		ModelAndView mv = new ModelAndView("redirect:/voluntario/listar");
		LOGGER.info("Retornando lista_voluntarios");
		return mv;
	}
	
	private void adicionarEmProjetos(Voluntario voluntario, List<Projeto> projetos) {
		projetos.forEach(projeto->{
			projeto.getVoluntarios().add(voluntario);
		});
		projetoRepository.save(projetos);
	}

}
