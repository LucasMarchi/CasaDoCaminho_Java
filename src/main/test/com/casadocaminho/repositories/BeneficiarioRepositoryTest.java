package com.casadocaminho.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.casadocaminho.builders.BeneficiarioBuilder;
import com.casadocaminho.models.Beneficiario;
import com.casadocaminho.utils.ConstantesTest;

@RunWith(SpringRunner.class)
@ActiveProfiles("teste")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class BeneficiarioRepositoryTest {
	
	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@Test
	public void deveCadastrarBeneficiario() {
		Beneficiario beneficiario = new BeneficiarioBuilder().novoBeneficiarioPadrao().criar();
		beneficiarioRepository.save(beneficiario);
		Assert.assertNotNull(beneficiarioRepository.findAll());
	}
	
	@Test
	public void deveRetornarBeneficiarios() {
		List<Beneficiario> beneficiarios = new BeneficiarioBuilder().novoBeneficiarioPadrao().criar(10);
		beneficiarioRepository.save(beneficiarios);
		List<Beneficiario> beneficiariosEncontrados = (List<Beneficiario>) beneficiarioRepository.findAll(); 
		Assert.assertEquals(10, beneficiariosEncontrados.size());
	}
	
	@Test
	public void deveEncontrarBeneficiarioPorNome() {
		Beneficiario beneficiario = new BeneficiarioBuilder().novoBeneficiarioPadrao().criar();
		beneficiarioRepository.save(beneficiario);
		Beneficiario BeneficiarioEncontrado = beneficiarioRepository.findByNome(ConstantesTest.BENEFICIARIO_NOME_TESTE).get(0);
		Assert.assertEquals(beneficiario.getNome(), BeneficiarioEncontrado.getNome());
	}
	
}
