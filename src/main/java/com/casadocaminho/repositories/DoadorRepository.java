package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Doador;

public interface DoadorRepository extends CrudRepository<Doador, Integer> {

	public List<Doador> findByNome(String nome);

}
