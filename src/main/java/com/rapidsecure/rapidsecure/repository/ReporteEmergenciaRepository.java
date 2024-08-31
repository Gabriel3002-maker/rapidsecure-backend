package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.dto.EmergencySummaryDTO;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import  java.util.List;

public interface ReporteEmergenciaRepository extends JpaRepository<ReporteEmergencia, Long> {

    Long countByEstadoIdAndFechaHoraReporteBetween(@Param("estadoId") Integer estadoId,
                                                   @Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);


    @Query(value = "SELECT re.id AS reporte_id, re.estado_id, e.nombre AS estado_nombre, " +
            "re.persona_id, p.nombre AS persona_nombre, re.tipo_emergencia_id, " +
            "te.descripcion AS tipo_emergencia_descripcion " +
            "FROM reporte_emergencia re " +
            "JOIN estado e ON re.estado_id = e.id " +
            "JOIN persona p ON re.persona_id = p.id " +
            "JOIN tipo_emergencia te ON re.tipo_emergencia_id = te.id " +
            "WHERE (:searchName IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :searchName, '%'))) " +
            "AND (:searchEstado IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :searchEstado, '%'))) " +
            "AND (:searchTipoEmergencia IS NULL OR LOWER(te.descripcion) LIKE LOWER(CONCAT('%', :searchTipoEmergencia, '%'))) " +
            "AND (:searchPersonaId IS NULL OR re.persona_id = :searchPersonaId)",
            nativeQuery = true)
    List<Object[]> buscarReportes(@Param("searchName") String searchName,
                                  @Param("searchEstado") String searchEstado,
                                  @Param("searchTipoEmergencia") String searchTipoEmergencia,
                                  @Param("searchPersonaId") Integer searchPersonaId);



}
