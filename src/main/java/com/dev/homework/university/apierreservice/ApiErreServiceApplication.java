package com.dev.homework.university.apierreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiErreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiErreServiceApplication.class, args);
    }

}

// TODO RESOLVER No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
// TODO TESTAR SWAGGER
// TODO CONFIGURAR RATELIMIT COM BUCKET4J
// TODO CONFIGURAR HTTPS
// TODO CONFIGURAR VERIFICAÇÃO EM DOIS FATORES COM GOOGLE AUTHENTICATOR, TOTP
// TODO IMPLEMENTAR CORS PROTECTION
// TODO IMPLEMENTAR HELMET
