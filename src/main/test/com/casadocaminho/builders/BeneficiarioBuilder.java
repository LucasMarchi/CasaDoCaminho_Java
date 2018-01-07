package com.casadocaminho.builders;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.casadocaminho.models.Beneficiario;
import com.casadocaminho.utils.ConstantesTest;

public class BeneficiarioBuilder {
	
private Beneficiario beneficiario = new Beneficiario();
	
	public BeneficiarioBuilder() {}
	
	public BeneficiarioBuilder novoVoluntario(String nome, LocalDate dataNascimento) {
		beneficiario.setNome(nome);
		beneficiario.setDataNascimento(dataNascimento);
		return this;
	}
	
	public BeneficiarioBuilder novoBeneficiarioPadrao() {
		beneficiario.setNome(ConstantesTest.BENEFICIARIO_NOME_TESTE);
		beneficiario.setDataNascimento(LocalDate.of(2000, Month.JANUARY, 01));
		return this;
	}
	
	public Beneficiario criar() {
		return beneficiario;
	}

	public List<Beneficiario> criar(int n) {
		
		List<Beneficiario> beneficiarios = new ArrayList<>();
		
		for(int i=0; i < n; i++) {
			Beneficiario beneficiarioAux = new BeneficiarioBuilder().novoBeneficiarioPadrao().criar();
			beneficiarios.add(beneficiarioAux);
		}
		
		return beneficiarios;
	}

}
