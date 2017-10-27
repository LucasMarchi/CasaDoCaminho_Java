package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.Beneficiario;

public interface BeneficiarioRepository extends CrudRepository<Beneficiario, Integer> {
	
	List<Beneficiario> findByNome(String beneficiario);

	Beneficiario findById(Integer idBeneficiario);
}
