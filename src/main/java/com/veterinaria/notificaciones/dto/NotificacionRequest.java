package com.veterinaria.notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NotificacionRequest {

    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "RECORDATORIO|CONFIRMACION|CANCELACION|RESULTADO",
            message = "Tipo inválido. Use: RECORDATORIO, CONFIRMACION, CANCELACION o RESULTADO")
    private String tipo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;
}