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

import com.casadocaminho.builders.DoadorBuilder;
import com.casadocaminho.models.Doador;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class DoadorRepositoryTest {
	
	@Autowired
	private DoadorRepository doadorRepository;
	
	@Test
	public void deveCadastrarDoador() {
		Doador doador = new DoadorBuilder().novoDoadorPadrao().criar();
		doadorRepository.save(doador);
		Assert.assertNotNull(doadorRepository.findAll());
	}
	
	@Test
	public void deveEncontrarDoadorPorNome() {
		Doador doador = new DoadorBuilder().novoDoadorPadrao().criar();
		doadorRepository.save(doador);
		Doador doadorEncontrado = doadorRepository.findByNome(doador.getNome()).get(0);
		Assert.assertEquals(doador.getNome(), doadorEncontrado.getNome());
	}
	
	@Test
	public void deveEncontrarDoadoresComMesmoNome() {
		List<Doador> doadores = new DoadorBuilder().novoDoadorPadrao().criar(5);
		doadorRepository.save(doadores);
		List<Doador> doadoresEncontrados = doadorRepository.findByNome(doador.getNome());
		Assert.assertEquals(doador.getNome(), doadorEncontrado.getNome());
	}

}
