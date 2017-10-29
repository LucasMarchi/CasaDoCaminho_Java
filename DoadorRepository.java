package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Doador;

public interface DoadorRepository extends CrudRepository<Doador, Integer> {

	public List<Doador> findByNome(String nome);
	
	@Query("SELECT d.doadores FROM Doadores d WHERE d.nome = ?1")
	List<Doador> findByDoadorNome(String nomeProjeto);
	
	@Query("SELECT d.doadores FROM Doadores d WHERE d.id = ?1")
	List<Doador> findByDoadorId(Integer idProjeto);

	Doador findById(Integer idDoador);

}
