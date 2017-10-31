package com.casadocaminho.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Beneficiario {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNascimento;
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Projeto> projeto;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Projeto> getProjeto() {
		return projeto;
	}

	public void setProjeto(List<Projeto> projeto) {
		this.projeto = projeto;
	}
}
