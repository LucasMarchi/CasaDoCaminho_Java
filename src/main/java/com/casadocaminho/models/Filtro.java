package com.casadocaminho.models;

public enum Filtro {

	NOME(1), 
	PROJETO(2);

	private final Integer valor;

	Filtro(Integer valorOpcao) {
		valor = valorOpcao;
	}

	public Integer getValor() {
		return valor;
	}

}
