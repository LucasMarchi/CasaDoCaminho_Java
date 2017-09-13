package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, Long>{

	List<Projeto> findByNome(String voluntarioTeste);

}
