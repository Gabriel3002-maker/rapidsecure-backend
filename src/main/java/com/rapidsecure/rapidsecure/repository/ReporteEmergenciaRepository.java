package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteEmergenciaRepository extends JpaRepository<ReporteEmergencia, Long> {
}
