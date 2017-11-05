package com.casadocaminho.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.casadocaminho.models.Projeto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
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
    public void deveRetornarTelaListagemAposCadastro() throws Exception {
        this.mockMvc
        .perform(post("/projeto/cadastrar")
        			.param("nome", "teste")
        			.param("dataInicio", "2017-01-01")
        			.param("dataTermino", "2017-12-12"))
                .andExpect(view().name("redirect:listar"));
    }
	
	@Test
    public void deveRetornarTelaFormularioAousCadastroInvalido() throws Exception {
        this.mockMvc
        .perform(post("/projeto/cadastrar"))
                .andExpect(view().name("projeto/form_projeto"));
    }

}
