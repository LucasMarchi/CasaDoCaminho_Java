package com.casadocaminho.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Beneficiario {
	
	@Id
	@GeneratedValue
	private long id;
	private String nome;

}
