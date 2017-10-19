package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;

public interface ProjetoRepository extends CrudRepository<Projeto, Integer>{

	List<Projeto> findByNome(String voluntarioTeste);

	Projeto findById(Integer idVoluntario);

}
