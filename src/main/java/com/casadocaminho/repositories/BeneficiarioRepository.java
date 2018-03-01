package com.casadocaminho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadocaminho.models.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
	
	List<Beneficiario> findByNome(String beneficiario);

	Beneficiario findById(Integer idBeneficiario);
}
