package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona , Long> {
    Optional<Persona> findByCorreo(String correo);
}
