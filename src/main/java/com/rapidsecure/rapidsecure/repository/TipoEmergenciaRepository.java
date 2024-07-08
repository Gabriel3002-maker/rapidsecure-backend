package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.entity.TipoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEmergenciaRepository extends JpaRepository<TipoEmergencia, Long> {
}
