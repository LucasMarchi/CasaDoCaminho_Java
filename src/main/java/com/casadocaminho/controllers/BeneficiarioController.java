package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casadocaminho.models.Beneficiario;
import com.casadocaminho.repositories.BeneficiarioRepository;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiarioController.class);
	
	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@RequestMapping("/form")
	public ModelAndView form(Beneficiario beneficiario) {
		return new ModelAndView("beneficiario/form_beneficiario");
	}
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Beneficiario beneficiario) {
		beneficiarioRepository.save(beneficiario);
		logger.info("Cadastro de Beneficiario");
		return new ModelAndView("beneficiario/lista_beneficiario");
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar(Model model) {
		ModelAndView mv = new ModelAndView("beneficiario/lista_beneficiario");
		model.addAttribute("beneficiarios", beneficiarioRepository.findAll());
		//mv.addObject("beneficiarios", beneficiarioRepository.findAll());
		logger.info("Retornando lista_beneficiario por filtro");
		return mv;
		
	}
	
	@RequestMapping("/editar")
	public ModelAndView editar() {
		return new ModelAndView("beneficiario/lista_beneficiario");
	}
}
