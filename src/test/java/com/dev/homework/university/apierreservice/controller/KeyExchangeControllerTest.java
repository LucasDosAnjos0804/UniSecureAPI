package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.dto.KeyExchangeRequest;
import com.dev.homework.university.apierreservice.dto.KeyExchangeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KeyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testKeyExchange() throws Exception {
        // Gerar um par de chaves para simular o cliente
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
        keyGen.initialize(2048);
        KeyPair clientKeyPair = keyGen.generateKeyPair();

        // Criar a requisição
        KeyExchangeRequest request = new KeyExchangeRequest();
        request.setClientPublicKey(Base64.getEncoder().encodeToString(clientKeyPair.getPublic().getEncoded()));

        // Enviar a requisição POST
        MvcResult result = mockMvc.perform(post("/key-exchange")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // Verificar a resposta
        String content = result.getResponse().getContentAsString();
        KeyExchangeResponse response = objectMapper.readValue(content, KeyExchangeResponse.class);

        assertNotNull(response.getServerPublicKey(), "A chave pública do servidor não deve ser nula");
        assertNotNull(response.getUserId(), "O ID do usuário não deve ser nulo");
        assertFalse(response.getServerPublicKey().isEmpty(), "A chave pública do servidor não deve estar vazia");
        assertFalse(response.getUserId().isEmpty(), "O ID do usuário não deve estar vazio");

        // Verificar se a chave pública do servidor é uma string Base64 válida
        byte[] serverPublicKeyBytes = Base64.getDecoder().decode(response.getServerPublicKey());
        assertTrue(serverPublicKeyBytes.length > 0, "A chave pública do servidor deve ser decodificável");
    }
}
