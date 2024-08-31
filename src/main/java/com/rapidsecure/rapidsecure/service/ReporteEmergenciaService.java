package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.dto.EmergencySummaryDTO;
import com.rapidsecure.rapidsecure.dto.EmergencySummaryResponse;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.repository.ReporteEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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





}
