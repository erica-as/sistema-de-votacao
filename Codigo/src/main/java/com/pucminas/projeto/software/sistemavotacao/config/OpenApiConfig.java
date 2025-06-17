package com.pucminas.projeto.software.sistemavotacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Votação API")
                        .description("API para gerenciamento de enquetes e votação")
                        .version("1.0")
                        .contact(new Contact()
                                .name("PUC Minas")
                                .email("contato@pucminas.br")));
    }
} 