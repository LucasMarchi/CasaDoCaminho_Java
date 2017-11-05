package com.casadocaminho.builders;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.utils.ConstantesTest;

public class ProjetoBuilder {

	private Projeto projeto = new Projeto();

	public ProjetoBuilder() {
	}

	public ProjetoBuilder novoProjeto(String nome, List<Voluntario> voluntarios) {
		projeto.setNome(nome);
		//projeto.setVoluntarios(voluntarios);
		return this;
	}

	public ProjetoBuilder novoProjetoPadrao() {
		projeto.setNome(ConstantesTest.PROJETO_TESTE);
		projeto.setDataInicio(LocalDate.of(1969, Month.MAY, 25));
		projeto.setDataTermino(LocalDate.now());
		projeto.setVoluntarios(new VoluntarioBuilder().novoVoluntarioPadrao().criar(5));
		return this;
	}

	public Projeto criar() {
		return projeto;
	}

	public List<Projeto> criar(int n) {

		List<Projeto> projetos = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			Projeto projetoAux = new ProjetoBuilder().novoProjetoPadrao().criar();
			projetos.add(projetoAux);
		}

		return projetos;
	}

}
