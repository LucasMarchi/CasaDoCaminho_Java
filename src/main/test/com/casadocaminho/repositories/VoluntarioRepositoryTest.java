package com.casadocaminho.repositories;

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

}
