package com.casadocaminho.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.casadocaminho.builders.VoluntarioBuilder;
import com.casadocaminho.models.Voluntario;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VoluntarioRepositoryTest {
	
	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@Test
	public void deveCadastrarVoluntario() {
		Voluntario voluntario = new VoluntarioBuilder().novoVoluntarioPadrao().criar();
		voluntarioRepository.save(voluntario);
		Assert.assertNotNull(voluntarioRepository.findAll());
	}
	
	@Test
	public void deveRetornarVoluntarios() {
		List<Voluntario> voluntarios = new ArrayList<>();
		for(int i=0; i < 10; i++) {
			voluntarios.add(new VoluntarioBuilder().novoVoluntarioPadrao().criar());
		}
		voluntarioRepository.save(voluntarios);
		List<Voluntario> voluntariosEncontrados = (List<Voluntario>) voluntarioRepository.findAll(); 
		Assert.assertEquals(10, voluntariosEncontrados.size());
	}

}
