package com.casadocaminho.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
public @Data class Projeto {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataInicio;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataTermino;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="projeto_voluntario", joinColumns = {@JoinColumn(name= "id_projeto")}, inverseJoinColumns = { @JoinColumn(name = "id_voluntario") })
	private List<Voluntario> voluntarios;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="projeto_beneficiario", joinColumns = {@JoinColumn(name= "id_beneficiario")}, inverseJoinColumns = { @JoinColumn(name = "id_projeto") })
	private List<Beneficiario> beneficiarios;

}
