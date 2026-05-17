package com.veterinaria.notificaciones.service;

import com.veterinaria.notificaciones.dto.NotificacionRequest;
import com.veterinaria.notificaciones.dto.NotificacionResponse;
import com.veterinaria.notificaciones.exception.ResourceNotFoundException;
import com.veterinaria.notificaciones.model.Notificacion;
import com.veterinaria.notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final UsuarioClientService usuarioClientService;

    public NotificacionResponse crear(NotificacionRequest request) {
        if (!usuarioClientService.existeUsuario(request.getUsuarioId())) {
            throw new IllegalArgumentException(
                    "El usuario con id " + request.getUsuarioId() + " no existe");
        }

        Notificacion n = Notificacion.builder()
                .tipo(request.getTipo())
                .mensaje(request.getMensaje())
                .estadoEnvio("PENDIENTE")
                .fechaEnvio(LocalDate.now())
                .usuarioId(request.getUsuarioId())
                .build();

        return toResponse(notificacionRepository.save(n));
    }

    public List<NotificacionResponse> listarTodas() {
        return notificacionRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public NotificacionResponse buscarPorId(Long id) {
        return notificacionRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notificación no encontrada con id: " + id));
    }

    public List<NotificacionResponse> listarPorUsuario(Long usuarioId) {
        return notificacionRepository
                .findByUsuarioIdOrderByFechaEnvioDesc(usuarioId)
                .stream().map(this::toResponse).toList();
    }

    public List<NotificacionResponse> listarPorEstado(String estadoEnvio) {
        return notificacionRepository.findByEstadoEnvio(estadoEnvio)
                .stream().map(this::toResponse).toList();
    }

    public NotificacionResponse marcarEnviada(Long id) {
        Notificacion n = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notificación no encontrada con id: " + id));
        n.setEstadoEnvio("ENVIADO");
        return toResponse(notificacionRepository.save(n));
    }

    public void eliminar(Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notificación no encontrada con id: " + id);
        }
        notificacionRepository.deleteById(id);
    }

    private NotificacionResponse toResponse(Notificacion n) {
        return NotificacionResponse.builder()
                .id(n.getId())
                .tipo(n.getTipo())
                .mensaje(n.getMensaje())
                .estadoEnvio(n.getEstadoEnvio())
                .fechaEnvio(n.getFechaEnvio())
                .usuarioId(n.getUsuarioId())
                .build();
    }
}