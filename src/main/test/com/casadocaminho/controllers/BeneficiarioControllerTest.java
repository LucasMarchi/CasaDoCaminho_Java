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
public class BeneficiarioControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void deveRetornarForm() throws Exception {
        this.mockMvc.perform(get("/beneficiario/form")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("beneficiario/form_beneficiario"));
    }
	
	@Test
    public void deveRetornarTelaListagem() throws Exception {
        this.mockMvc.perform(get("/beneficiario/listar")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("beneficiario/lista_beneficiarios"));
    }

}