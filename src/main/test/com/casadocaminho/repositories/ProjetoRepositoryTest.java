package com.casadocaminho.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.casadocaminho.builders.ProjetoBuilder;
import com.casadocaminho.models.Projeto;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProjetoRepositoryTest {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Test
	public void deveCadastrarProjeto() {
		Projeto projeto = new ProjetoBuilder().novoProjetoPadrao().criar();
		projetoRepository.save(projeto);
		Assert.assertNotNull(projetoRepository.findAll());
	}

}
