package com.casadocaminho.builders;

import java.util.ArrayList;
import java.util.List;

import com.casadocaminho.models.Doador;

public class DoadorBuilder {

	private Doador doador = new Doador();

	public DoadorBuilder() {}

	public DoadorBuilder novoDoador(String nome, String email, String telefone) {
		doador.setNome(nome);
		doador.setEmail(email);
		doador.setTelefone(telefone);
		return this;
	}

	public DoadorBuilder novoDoadorPadrao() {
		doador.setNome("Fulano");
		doador.setEmail("teste@teste.com");
		doador.setTelefone("(11)4444-5555");
		return this;
	}

	public Doador criar() {
		return doador;
	}
	
	public List<Doador> criar(int n) {
		
		List<Doador> doadores = new ArrayList<>();
		
		for(int i=0; i < n; i++) {
			Doador doadorAux = doador;
			doadores.add(doadorAux);
		}
		
		return doadores;
	}

}
