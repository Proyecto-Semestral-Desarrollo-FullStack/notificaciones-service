package com.veterinaria.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String tipo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private String estadoEnvio;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @NotNull
    @Column(nullable = false)
    private Long usuarioId;
}