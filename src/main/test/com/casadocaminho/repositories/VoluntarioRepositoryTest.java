package com.casadocaminho.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.casadocaminho.builders.ProjetoBuilder;
import com.casadocaminho.builders.VoluntarioBuilder;
import com.casadocaminho.models.Projeto;
import com.casadocaminho.models.Voluntario;
import com.casadocaminho.utils.ConstantesTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VoluntarioRepositoryTest {
	
	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Test
	public void deveCadastrarVoluntario() {
		Voluntario voluntario = new VoluntarioBuilder().novoVoluntarioPadrao().criar();
		voluntarioRepository.save(voluntario);
		Assert.assertNotNull(voluntarioRepository.findAll());
	}
	
	@Test
	public void deveRetornarVoluntarios() {
		List<Voluntario> voluntarios = new VoluntarioBuilder().novoVoluntarioPadrao().criar(10);
		voluntarioRepository.save(voluntarios);
		List<Voluntario> voluntariosEncontrados = (List<Voluntario>) voluntarioRepository.findAll(); 
		Assert.assertEquals(10, voluntariosEncontrados.size());
	}
	
	@Test
	public void deveEncontrarVoluntarioPorNome() {
		Voluntario voluntario = new VoluntarioBuilder().novoVoluntarioPadrao().criar();
		voluntarioRepository.save(voluntario);
		Voluntario voluntarioEncontrado = voluntarioRepository.findByNome(ConstantesTest.VOLUNTARIO_TESTE).get(0);
		Assert.assertEquals(voluntario.getNome(), voluntarioEncontrado.getNome());
	}
	
	@Test
	public void deveEncontrarVoluntariosComMesmoNome() {
		List<Voluntario> voluntarios = new VoluntarioBuilder().novoVoluntarioPadrao().criar(2);
		voluntarioRepository.save(voluntarios);
		List<Voluntario> voluntariosEncontrados = voluntarioRepository.findByNome(ConstantesTest.VOLUNTARIO_TESTE);
		Assert.assertEquals(2, voluntariosEncontrados.size());
	}
	
	@Test
	public void deveEncontrarVoluntariosPorProjeto() {
		Projeto projeto = new ProjetoBuilder().novoProjetoPadrao().criar();
		projetoRepository.save(projeto);
//		List<Voluntario> voluntariosEncontrados = voluntarioRepository.findByProjetoNome(ConstantesTest.PROJETO_TESTE);
//		Assert.assertNotNull(voluntariosEncontrados);
	}
}
