package com.rapidsecure.rapidsecure.repository;

import com.rapidsecure.rapidsecure.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // No es necesario implementar métodos CRUD aquí,
    // JpaRepository ya proporciona métodos como save(), findById(), deleteById(), etc.
}
