package com.casadocaminho.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Doador {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	
}
