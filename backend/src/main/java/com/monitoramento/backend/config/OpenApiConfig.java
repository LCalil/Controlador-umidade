package com.monitoramento.backend.config;

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
                        .title("Sistema de Monitoramento de Umidade de Plantas")
                        .version("1.0.0")
                        .description("API REST para monitoramento de umidade de solo e acionamento de alertas para sistemas embarcados.")
                        .contact(new Contact()
                                .name("Monitoramento de Plantas")));
    }
}
