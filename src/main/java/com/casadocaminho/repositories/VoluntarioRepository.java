package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Voluntario;

public interface VoluntarioRepository extends CrudRepository<Voluntario, Long> {

	List<Voluntario> findByNome(String nome);

	@Query("SELECT p.voluntarios FROM Projeto p WHERE p.nome = ?1")
	List<Voluntario> findByProjetoNome(String nomeProjeto);

}
