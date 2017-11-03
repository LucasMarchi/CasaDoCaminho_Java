package com.casadocaminho.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/voluntario")
public class VoluntarioController {

	private static final Logger logger = LoggerFactory.getLogger(VoluntarioController.class);

	@Autowired
	private VoluntarioRepository voluntarioRepository;

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(new VoluntarioValidator());
    }

	@RequestMapping("/form")
	public ModelAndView form(Voluntario voluntario) {
		ModelAndView mv = new ModelAndView("voluntario/form_voluntario");
		mv.addObject("projetos", projetoRepository.findAll());
		return mv;
	}

	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(@Valid @ModelAttribute Voluntario voluntario, BindingResult bindingResult) {
		ModelAndView mv;
		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("voluntario/form_voluntario"); 
			mv.addObject("projetos", projetoRepository.findAll());
			return mv;
		}
		voluntarioRepository.save(voluntario);
		List<Projeto> projetos = voluntario.getProjetos();
		if(!projetos.isEmpty()) adicionarEmProjetos(voluntario, projetos);
		mv = new ModelAndView("redirect:listar");
		return mv;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("voluntario/lista_voluntarios");
		mv.addObject("voluntarios", voluntarioRepository.findAll());
		logger.info("Retornando lista_voluntarios");
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
		mv.addObject("listaProjetos", projetoRepository.findAll());
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
	
	private void adicionarEmProjetos(Voluntario voluntario, List<Projeto> projetos) {
		projetos.forEach(projeto->{
			projeto.getVoluntarios().add(voluntario);
		});
		projetoRepository.save(projetos);
	}

}
