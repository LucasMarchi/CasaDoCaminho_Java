package com.casadocaminho.builders;

import java.util.List;

import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.utils.Constantes;

public class ProjetoBuilder {

	
	private Projeto projeto = new Projeto();

	public ProjetoBuilder() {}

	public ProjetoBuilder novoProjeto(String nome, List<Voluntario> voluntarios) {
		projeto.setNome(nome);
		projeto.setVoluntarios(voluntarios);
		return this;
	}

	public ProjetoBuilder novoProjetoPadrao() {
		projeto.setNome(Constantes.PROJETO_TESTE);
		projeto.setVoluntarios(new VoluntarioBuilder().novoVoluntarioPadrao().criar(5));
		return this;
	}

	public Projeto criar() {
		return projeto;
	}

}
