package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.ApiResponse;
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
@Tag(name="TipoEmergencia", description = "TipoEmergencia")
public class TipoEmergenciaController {
    @Autowired
    private TipoEmergenciaService tipoEmergenciaService;

    @GetMapping
    @Operation(summary = "Obtener", description = "Obtener Tipo de Emergencia Disponibles")
    public ResponseEntity<ApiResponse<List<TipoEmergencia>>> obtenerTiposEmergencia() {
        try {
            List<TipoEmergencia> tiposEmergencia = tipoEmergenciaService.obtenerTiposEmergencia();
            if (tiposEmergencia.isEmpty()) {
                ApiResponse<List<TipoEmergencia>> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "No se encontraron tipos de emergencia",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ApiResponse<List<TipoEmergencia>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Tipos de emergencia obtenidos correctamente",
                    true,
                    tiposEmergencia
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<TipoEmergencia>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener tipos de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Tipo de Emergencia por Id")
    public ResponseEntity<ApiResponse<TipoEmergencia>> obtenerTipoEmergenciaPorId(@PathVariable Long id) {
        try {
            Optional<TipoEmergencia> tipoEmergencia = tipoEmergenciaService.obtenerTipoEmergenciaPorId(id);
            if (tipoEmergencia.isPresent()) {
                ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Tipo de emergencia encontrado",
                        true,
                        tipoEmergencia.get()
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Tipo de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener tipo de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    @Operation(summary = "Crear", description = "Crear Tipo de Emergencia")
    public ResponseEntity<ApiResponse<TipoEmergencia>> crearTipoEmergencia(@RequestBody TipoEmergencia tipoEmergencia) {
        try {
            TipoEmergencia nuevoTipoEmergencia = tipoEmergenciaService.guardarTipoEmergencia(tipoEmergencia);
            ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Tipo de emergencia creado correctamente",
                    true,
                    nuevoTipoEmergencia
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al crear tipo de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Tipo de Emergencia")
    public ResponseEntity<ApiResponse<TipoEmergencia>> actualizarTipoEmergencia(@PathVariable Long id, @RequestBody TipoEmergencia tipoEmergencia) {
        try {
            if (!tipoEmergenciaService.obtenerTipoEmergenciaPorId(id).isPresent()) {
                ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Tipo de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            tipoEmergencia.setId(id); // Asegura que se actualiza el tipo correcto
            TipoEmergencia tipoEmergenciaActualizado = tipoEmergenciaService.guardarTipoEmergencia(tipoEmergencia);
            ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Tipo de emergencia actualizado correctamente",
                    true,
                    tipoEmergenciaActualizado
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<TipoEmergencia> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al actualizar tipo de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Tipo de Emergencia")
    public ResponseEntity<ApiResponse<Void>> eliminarTipoEmergencia(@PathVariable Long id) {
        try {
            if (!tipoEmergenciaService.obtenerTipoEmergenciaPorId(id).isPresent()) {
                ApiResponse<Void> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Tipo de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            tipoEmergenciaService.eliminarTipoEmergencia(id);
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.NO_CONTENT.value(),
                    "Tipo de emergencia eliminado correctamente",
                    true,
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al eliminar tipo de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
