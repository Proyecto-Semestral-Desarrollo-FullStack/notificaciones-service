package com.veterinaria.notificaciones.controller;

import com.veterinaria.notificaciones.dto.NotificacionRequest;
import com.veterinaria.notificaciones.dto.NotificacionResponse;
import com.veterinaria.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponse> crear(
            @Valid @RequestBody NotificacionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponse>> listar() {
        return ResponseEntity.ok(notificacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacionResponse>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/estado/{estadoEnvio}")
    public ResponseEntity<List<NotificacionResponse>> listarPorEstado(
            @PathVariable String estadoEnvio) {
        return ResponseEntity.ok(notificacionService.listarPorEstado(estadoEnvio));
    }

    @PatchMapping("/{id}/marcar-enviada")
    public ResponseEntity<NotificacionResponse> marcarEnviada(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarEnviada(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}