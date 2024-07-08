package com.rapidsecure.rapidsecure.controller;


import com.rapidsecure.rapidsecure.entity.Rol;
import com.rapidsecure.rapidsecure.entity.TipoEmergencia;
import com.rapidsecure.rapidsecure.service.TipoEmergenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoEmergencia")
@Tag(name="TipoEmergencia" , description = "TipoEmergencia")
public class TipoEmergenciaController {
    @Autowired
    private TipoEmergenciaService tipoEmergenciaService;


    @GetMapping
    @Operation(summary = "Obtener", description = "Obtner Tipo de Emergencia Disponibles")
    public List<TipoEmergencia> obtenerTiposEmergencia(){
        return  tipoEmergenciaService.obtenerTiposEmergencia();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Tipo de Emergencia  por Id")
    public ResponseEntity<TipoEmergencia> obtenerTipoEmergenciaPorId(@PathVariable Long id) {
        Optional<TipoEmergencia> tipoEmergencia = tipoEmergenciaService.obtenerTipoEmergenciaPorId(id);
        return tipoEmergencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear", description = "Crear Tipo de Emergencia")
    public ResponseEntity<TipoEmergencia> crearTipoEmergencia(@RequestBody TipoEmergencia tipoEmergencia) {
        TipoEmergencia guardarTipoEmergencia = tipoEmergenciaService.guardarTipoEmergencia(tipoEmergencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardarTipoEmergencia);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Tipo de Emergencia ")
    public ResponseEntity<TipoEmergencia> actualizarTipoEmergencia(@PathVariable Long id, @RequestBody TipoEmergencia tipoEmergencia ) {
        if (!tipoEmergenciaService.obtenerTipoEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoEmergencia.setId(id);  // asegura que se actualiza el producto correcto
        TipoEmergencia tipoEmergenciaActualizado = tipoEmergenciaService.guardarTipoEmergencia(tipoEmergencia);
        return ResponseEntity.ok(tipoEmergenciaActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Tipo Emergencia ")
    public ResponseEntity<Void> eliminarTipoEmergencia(@PathVariable Long id) {
        if (!tipoEmergenciaService.obtenerTipoEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoEmergenciaService.eliminarTipoEmergencia(id);
        return ResponseEntity.noContent().build();
    }

}
