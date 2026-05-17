package com.veterinaria.notificaciones.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UsuarioClientService {

    private final WebClient usuariosWebClient;

    public UsuarioClientService(@Qualifier("usuariosWebClient") WebClient usuariosWebClient) {
        this.usuariosWebClient = usuariosWebClient;
    }

    public boolean existeUsuario(Long usuarioId) {
        try {
            Boolean existe = usuariosWebClient.get()
                    .uri("/api/usuarios/existe/{id}", usuarioId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return Boolean.TRUE.equals(existe);
        } catch (Exception e) {
            System.err.println("Error conectando con usuarios-service: " + e.getMessage());
            return false;
        }
    }
}