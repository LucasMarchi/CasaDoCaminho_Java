package com.casadocaminho.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Doador {
	
	@Id
	@GeneratedValue
	private Integer id;
	// Tipo 0 = Pessoa Fisica
	// Tipo 1 = Pessoa Juridica
	private Integer tipoDoador;
	// CNPJ ou CPF
	private String documentoIdentificador;
	private String nome;
	private String email;
	private String telefone;
	private List<String> reciboDeDoacao;
	
	
	public Integer getTipoDoador() {
		return tipoDoador;
	}
	public void setTipoDoador(Integer tipoDoador) {
		this.tipoDoador = tipoDoador;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getDocumentoIdentificador() {
		return documentoIdentificador;
	}
	public void setDocumentoIdentificador(String documentoIdentificador) {
		this.documentoIdentificador = documentoIdentificador;
	}
	public List<String> getReciboDeDoacao() {
		return reciboDeDoacao;
	}
	public void setReciboDeDoacao(List<String> reciboDeDoacao) {
		this.reciboDeDoacao = reciboDeDoacao;
	}
	
}
