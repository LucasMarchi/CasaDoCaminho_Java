package com.casadocaminho.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
public @Data class Voluntario {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	@ManyToMany(mappedBy="voluntarios")
	private List<Projeto> projetos;
	
}
