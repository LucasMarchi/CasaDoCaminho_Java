package com.casadocaminho.models;

import java.time.LocalDate;
import java.util.ArrayList;
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

@Entity
public class Projeto {

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
		if(voluntarios == null) voluntarios = new ArrayList<>();
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(List<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
}
