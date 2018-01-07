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

import com.casadocaminho.builders.ProjetoBuilder;
import com.casadocaminho.models.Projeto;
import com.casadocaminho.utils.ConstantesTest;

@RunWith(SpringRunner.class)
@ActiveProfiles("teste")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class ProjetoRepositoryTest {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Test
	public void deveCadastrarProjeto() {
		Projeto projeto = new ProjetoBuilder().novoProjetoPadrao().criar();
		projetoRepository.save(projeto);
		Assert.assertNotNull(projetoRepository.findAll());
	}
	
	@Test
	public void deveRetornarProjetos() {
		List<Projeto> projetos = new ProjetoBuilder().novoProjetoPadrao().criar(10);
		projetoRepository.save(projetos);
		List<Projeto> projetosEncontrados = (List<Projeto>) projetoRepository.findAll(); 
		Assert.assertEquals(10, projetosEncontrados.size());
	}
	
	@Test
	public void deveEncontrarProjetoPorNome() {
		Projeto projeto = new ProjetoBuilder().novoProjetoPadrao().criar();
		projetoRepository.save(projeto);
		Projeto projetoEncontrado = projetoRepository.findByNome(ConstantesTest.PROJETO_NOME_TESTE).get(0);
		Assert.assertEquals(projeto.getNome(), projetoEncontrado.getNome());
	}

}
