package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.dto.ReporteEmergenciaResponseDTO;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.repository.ReporteEmergenciaRepository;
import com.rapidsecure.rapidsecure.websocket.NotificationWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteEmergenciaService {

    @Autowired
    private ReporteEmergenciaRepository reporteEmergenciaRepository;

    @Autowired
    private NotificationWebSocketHandler notificationWebSocketHandler; // Inyección de dependencia

    public List<ReporteEmergencia> obtenerReporteEmergencia() {
        return reporteEmergenciaRepository.findAll();
    }

    public Optional<ReporteEmergencia> obtenerReporteEmergenciaPorId(Long id) {
        return reporteEmergenciaRepository.findById(id);
    }

    public ReporteEmergencia guardarEmergencia(ReporteEmergencia reporteEmergencia) {
        ReporteEmergencia nuevoReporteEmergencia = reporteEmergenciaRepository.save(reporteEmergencia);

        try {
            // Construir mensaje de notificación
            String mensaje = String.format(
                    "Nueva emergencia registrada: ID %d,  Descripción: %s, Ubicación: %s, Latitud: %.6f, Longitud: %.6f, Fecha y Hora: %s ",
                    nuevoReporteEmergencia.getId(),
                    nuevoReporteEmergencia.getDescripcion(),
                    nuevoReporteEmergencia.getUbicacion(),
                    nuevoReporteEmergencia.getLatitud(),
                    nuevoReporteEmergencia.getLongitud(),
                    nuevoReporteEmergencia.getFechaHoraReporte(),
                    nuevoReporteEmergencia.getEstado()
            );

            // Enviar notificación a los clientes
            notificationWebSocketHandler.sendNotification(mensaje);
        } catch (IOException e) {
            e.printStackTrace(); // Registra la excepción o maneja el error según sea necesario
        }

        return nuevoReporteEmergencia;
    }

    public void eliminarReporteEmergencia(Long id) {
        reporteEmergenciaRepository.deleteById(id);
    }

    public Long obtenerConteoEmergencias(Integer estadoId, LocalDateTime startDate, LocalDateTime endDate) {
        return reporteEmergenciaRepository.countByEstadoIdAndFechaHoraReporteBetween(estadoId, startDate, endDate);
    }

    public List<ReporteEmergenciaResponseDTO> buscarReportes(String searchName, String searchEstado, String searchTipoEmergencia, Integer searchPersonaId) {
        List<Object[]> results = reporteEmergenciaRepository.buscarReportes(searchName, searchEstado, searchTipoEmergencia, searchPersonaId);

        return results.stream()
                .map(result -> new ReporteEmergenciaResponseDTO(
                        ((Number) result[0]).longValue(),   // reporte_id
                        ((Number) result[1]).longValue(),   // estado_id
                        (String) result[2],                // estado_nombre
                        ((Number) result[3]).longValue(),   // persona_id
                        (String) result[4],                // persona_nombre
                        ((Number) result[5]).longValue(),   // tipo_emergencia_id
                        (String) result[6]                 // tipo_emergencia_descripcion
                ))
                .collect(Collectors.toList());
    }
}
