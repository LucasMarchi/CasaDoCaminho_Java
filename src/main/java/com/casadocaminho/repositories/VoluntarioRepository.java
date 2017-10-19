package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Voluntario;

public interface VoluntarioRepository extends CrudRepository<Voluntario, Integer> {

	List<Voluntario> findByNome(String nomeVoluntario);

	@Query("SELECT p.voluntarios FROM Projeto p WHERE p.nome = ?1")
	List<Voluntario> findByProjetoNome(String nomeProjeto);
	
	@Query("SELECT p.voluntarios FROM Projeto p WHERE p.id = ?1")
	List<Voluntario> findByProjetoId(Integer idProjeto);

	Voluntario findById(Integer idVoluntario);

}
