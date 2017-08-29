package com.casadocaminho.builders;

import java.util.ArrayList;
import java.util.List;

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
		voluntario.setNome("VoluntarioTeste");
		voluntario.setEmail("teste@teste.com");
		voluntario.setTelefone("(11)4444-5555");
		return this;
	}
	
	public Voluntario criar() {
		return voluntario;
	}

	public List<Voluntario> criar(int n) {
		
		List<Voluntario> voluntarios = new ArrayList<>();
		
		for(int i=0; i < n; i++) {
			Voluntario voluntarioAux = new VoluntarioBuilder().novoVoluntarioPadrao().criar();
			voluntarios.add(voluntarioAux);
		}
		
		return voluntarios;
	}
}
