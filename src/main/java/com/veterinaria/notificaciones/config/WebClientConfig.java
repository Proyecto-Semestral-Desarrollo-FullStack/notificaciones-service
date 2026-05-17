package com.veterinaria.notificaciones.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${services.usuarios-service.url}")
    private String usuariosUrl;

    @Bean("usuariosWebClient")
    public WebClient usuariosWebClient() {
        return WebClient.builder().baseUrl(usuariosUrl).build();
    }
}