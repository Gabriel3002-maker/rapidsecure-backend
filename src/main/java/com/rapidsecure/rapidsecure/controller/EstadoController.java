package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.entity.Estado;
import com.rapidsecure.rapidsecure.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
@Tag(name= "Estado" , description = "Estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    @Operation(summary = "Obtener", description = "Obtener Estados Disponibles")
    public List<Estado> obtenerTodosLosEstados(){
        return  estadoService.obtenerTodosLosEstados();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Estado por Id")
    public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable Long id) {
        Optional<Estado> estado = estadoService.obtenerEstadosPorId(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear", description = "Crear Estado")
    public ResponseEntity<Estado> crearEstado(@RequestBody Estado estado) {
        Estado nuevoEstado = estadoService.guardarEstado(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Estado")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Long id, @RequestBody Estado estado) {
        if (!estadoService.obtenerEstadosPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estado.setId(id);  // asegura que se actualiza el producto correcto
        Estado estadoActualizado = estadoService.guardarEstado(estado);
        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Estado")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        if (!estadoService.obtenerEstadosPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }
}
