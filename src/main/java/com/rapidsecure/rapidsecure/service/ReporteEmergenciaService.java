package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.dto.EmergencySummaryDTO;
import com.rapidsecure.rapidsecure.dto.EmergencySummaryResponse;
import com.rapidsecure.rapidsecure.dto.ReporteEmergenciaResponseDTO;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.repository.ReporteEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteEmergenciaService {
    @Autowired
    private ReporteEmergenciaRepository reporteEmergenciaRepository;

    public List<ReporteEmergencia> obtenerReporteEmergencia(){
        return  reporteEmergenciaRepository.findAll();
    }

    public Optional<ReporteEmergencia> obtenerReporteEmergenciaPorId(Long id){
        return  reporteEmergenciaRepository.findById(id);
    }

    public ReporteEmergencia guardarEmergencia(ReporteEmergencia reporteEmergencia){
        return reporteEmergenciaRepository.save(reporteEmergencia);
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
