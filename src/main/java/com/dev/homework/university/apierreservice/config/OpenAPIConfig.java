package com.dev.homework.university.apierreservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig  {

//    @Value("${bezkoder.openapi.dev-url}") // TODO isso pode mudar
//    private String devUrl;
//
//    @Value("${bezkoder.openapi.prod-url}") // TODO isso pode mudar
//    private String prodUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI myOpenAPI() {

//        Server devServer = new Server();
//        devServer.setUrl(devUrl);
//        devServer.setDescription("Server URL in Development environment");
//
//        Server prodServer = new Server();
//        prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("dupla.de.redes@gmail.com");
        contact.setName("Lucas e José");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Security Messenger API")
                .version("1.0")
                .contact(contact)
                .description("Api desenvolvida para um trabalho de REDES de computadores")
                .license(mitLicense);

//        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));

        return new OpenAPI().info(info);
    }
}