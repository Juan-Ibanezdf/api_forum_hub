package br.com.alura.forum.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpingDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API-REST Forum Hub")
                        .version("v1")
                        .description("A Forum Hub API é uma aplicação de fórum construída como parte do curso **Praticando Spring Framework: Challenge Fórum Hub** da Alura. Este projeto tem como objetivo consolidar conhecimentos sobre desenvolvimento de aplicações REST com Spring Boot, incluindo autenticação e autorização com Spring Security, persistência de dados com Hibernate, migrações de banco de dados com Flyway e documentação da API com Springdoc OpenAPI. A aplicação permite que os usuários realizem autenticação, criem cursos, postem tópicos e respostas relacionados aos cursos, e visualizem informações sobre cursos, tópicos e respostas de maneira eficiente.")
                        .contact(new Contact()
                                .name("Juan Daniel Ferreira Ibanez")
                                .email("juan.ibanezdf@gmail.com")
                                .url("https://github.com/Juan-Ibanezdf"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
