package com.dev.homework.university.apierreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication()
public class ApiErreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiErreServiceApplication.class, args);
    }

    // TODO TESTAR SWAGGER
    // TODO CONFIGURAR A TROCA DE CHAVES DE ENCRIPTAÇÃO
    // TODO CONFIGURAR A TROCA DE MENSAGEM ENCRIPTADA
}

