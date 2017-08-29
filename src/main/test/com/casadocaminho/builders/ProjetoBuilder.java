package com.casadocaminho.builders;

import java.util.ArrayList;
import java.util.List;

import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;

public class ProjetoBuilder {

	private Projeto projeto = new Projeto();

	public ProjetoBuilder() {}

	public ProjetoBuilder novoProjeto(String nome, List<Voluntario> voluntarios) {
		projeto.setNome(nome);
		projeto.setVoluntarios(voluntarios);
		return this;
	}

	public ProjetoBuilder novoProjetoPadrao() {
		projeto.setNome("ProjetoTeste");
		projeto.setVoluntarios(criarVoluntarios());
		return this;
	}

	private List<Voluntario> criarVoluntarios() {
		List<Voluntario> voluntarios = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			voluntarios.add(new VoluntarioBuilder().novoVoluntarioPadrao().criar());
		}
		return voluntarios;
	}

	public Projeto criar() {
		return projeto;
	}

}
