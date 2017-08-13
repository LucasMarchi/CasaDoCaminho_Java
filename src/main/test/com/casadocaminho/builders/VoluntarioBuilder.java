package com.casadocaminho.builders;

import org.springframework.beans.factory.annotation.Autowired;

import com.casadocaminho.models.Voluntario;

public class VoluntarioBuilder {
	
	private Voluntario voluntario = new Voluntario();
	
	public VoluntarioBuilder() {}
	
	public VoluntarioBuilder novoVoluntario(String nome, String email, String telefone) {
		voluntario.setNome(nome);
		voluntario.setEmail(email);
		voluntario.setTelefone(telefone);
		return this;
	}
	
	public VoluntarioBuilder novoVoluntarioPadrao() {
		voluntario.setNome("Fulano");
		voluntario.setEmail("teste@teste.com");
		voluntario.setTelefone("(11)4444-5555");
		return this;
	}
	
	public Voluntario criar() {
		return voluntario;
	}
}
