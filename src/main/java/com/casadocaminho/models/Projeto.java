package com.casadocaminho.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Projeto {

	@Id
	@GeneratedValue
	private Integer id;
	@NotEmpty
	private String nome;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataInicio;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataTermino;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Voluntario> voluntarios;

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
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}
}
