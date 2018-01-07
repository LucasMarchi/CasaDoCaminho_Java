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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Beneficiario;
import com.casadocaminho.models.Projeto;
import com.casadocaminho.repositories.BeneficiarioRepository;
import com.casadocaminho.repositories.ProjetoRepository;
import com.casadocaminho.validators.BeneficiarioValidator;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeneficiarioController.class);
	
	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(new BeneficiarioValidator());
    }
	
	@RequestMapping("/form")
	public ModelAndView form(Beneficiario beneficiario) {
		ModelAndView mv = new ModelAndView("beneficiario/form_beneficiario");
		mv.addObject("projetos", projetoRepository.findAll());
		return mv;
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(@Valid @ModelAttribute Beneficiario beneficiario, BindingResult bindingResult) {
		ModelAndView mv;
		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("beneficiario/form_beneficiario"); 
			mv.addObject("projetos", projetoRepository.findAll());
			return mv;
		}
		beneficiarioRepository.save(beneficiario);
		List<Projeto> projetos = beneficiario.getProjetos();
		if(!projetos.isEmpty()) adicionarEmProjetos(beneficiario, projetos);
		return new ModelAndView("redirect:listar");
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("beneficiario/lista_beneficiarios");
		mv.addObject("beneficiarios", beneficiarioRepository.findAll());
		LOGGER.info("Retornando lista_beneficiarios");
		return mv;
		
	}
	
	@RequestMapping("/editar")
	public ModelAndView editar() {
		return new ModelAndView("beneficiario/lista_beneficiarios");
	}
	
	private void adicionarEmProjetos(Beneficiario beneficiario, List<Projeto> projetos) {
		projetos.forEach(projeto->{
			projeto.getBeneficiarios().add(beneficiario);
		});
		projetoRepository.save(projetos);
	}
}
