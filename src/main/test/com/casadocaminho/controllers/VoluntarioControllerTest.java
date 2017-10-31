package com.casadocaminho.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VoluntarioControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void deveRetornarTelaFormulario() throws Exception {
        this.mockMvc.perform(get("/voluntario/form")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("/voluntario/form_voluntario"));
    }
	
	@Test
    public void deveRetornarTelaListagem() throws Exception {
        this.mockMvc.perform(get("/voluntario")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("/voluntario/lista_voluntarios"));
    }
	

}
