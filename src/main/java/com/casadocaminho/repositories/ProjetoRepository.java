package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.casadocaminho.models.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, Integer>{

	List<Projeto> findByNome(String nomeVoluntario);

	Projeto findById(Integer idProjeto);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM projeto_voluntario WHERE id_voluntario = ?1", nativeQuery = true)
	void deleteVoluntarioFromProjeto(Integer idVoluntario);

}
