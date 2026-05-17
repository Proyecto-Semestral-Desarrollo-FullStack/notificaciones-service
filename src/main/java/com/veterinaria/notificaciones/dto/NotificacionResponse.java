package com.veterinaria.notificaciones.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponse {
    private Long id;
    private String tipo;
    private String mensaje;
    private String estadoEnvio;
    private LocalDate fechaEnvio;
    private Long usuarioId;
}