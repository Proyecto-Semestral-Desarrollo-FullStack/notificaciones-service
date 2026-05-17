package com.veterinaria.notificaciones.repository;

import com.veterinaria.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioId(Long usuarioId);
    List<Notificacion> findByEstadoEnvio(String estadoEnvio);
    List<Notificacion> findByTipo(String tipo);
    List<Notificacion> findByUsuarioIdOrderByFechaEnvioDesc(Long usuarioId);
}