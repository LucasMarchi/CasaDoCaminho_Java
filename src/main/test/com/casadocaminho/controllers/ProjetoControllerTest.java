package com.casadocaminho.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.casadocaminho.models.Projeto;

@RunWith(SpringRunner.class)
@ActiveProfiles("teste")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@WithMockUser(username = "admin", roles={"ADMIN"})
public class ProjetoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Projeto projeto;

	@Test
	public void deveRetornarTelaFormulario() throws Exception {
		this.mockMvc.perform(get("/projeto/form")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("projeto/form_projeto"));
	}

	@Test
	public void deveRetornarTelaListagem() throws Exception {
		this.mockMvc.perform(get("/projeto/listar")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("projeto/lista_projetos"));
	}

}
