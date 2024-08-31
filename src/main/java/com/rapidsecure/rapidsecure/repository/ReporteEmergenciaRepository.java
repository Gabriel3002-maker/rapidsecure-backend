package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.dto.EmergencySummaryDTO;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReporteEmergenciaRepository extends JpaRepository<ReporteEmergencia, Long> {

    Long countByEstadoIdAndFechaHoraReporteBetween(@Param("estadoId") Integer estadoId,
                                                   @Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);






}
