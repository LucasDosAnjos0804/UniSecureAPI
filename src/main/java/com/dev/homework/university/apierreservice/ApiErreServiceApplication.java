package com.dev.homework.university.apierreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApiErreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiErreServiceApplication.class, args);
    }

    // TODO CONFIGURAR SWAGGER
    // TODO CONFIGURAR RATELIMIT COM BUCKET4J
    // TODO CONFIGURAR HTTPS
    // TODO CONFIGURAR VERIFICAÇÃO EM DOIS FATORES COM GOOGLE AUTHENTICATOR, TOTP
    // TODO IMPLEMENTAR CORS PROTECTION
    // TODO IMPLEMENTAR HELMET
}
