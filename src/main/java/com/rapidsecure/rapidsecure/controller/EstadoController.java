package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Estado>>> obtenerTodosLosEstados() {
        try {
            List<Estado> estados = estadoService.obtenerTodosLosEstados();
            if (estados == null || estados.isEmpty()) {
                ApiResponse<List<Estado>> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "No se encontraron estados",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Estado>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Estados obtenidos correctamente",
                    true,
                    estados
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse<List<Estado>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener estados: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Estado por Id")
    public ResponseEntity<ApiResponse<Estado>> obtenerEstadoPorId(@PathVariable Long id) {
        try {
            Optional<Estado> estado = estadoService.obtenerEstadosPorId(id);

            if (estado.isPresent()) {
                // Devuelve una respuesta exitosa con el estado
                ApiResponse<Estado> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Estado encontrado",
                        true,
                        estado.get()
                );
                return ResponseEntity.ok(response);
            } else {
                // Devuelve un error 404 si el estado no se encuentra
                ApiResponse<Estado> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Estado no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir
            ApiResponse<Estado> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener estado: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @PostMapping
    @Operation(summary = "Crear", description = "Crear Estado")
    public ResponseEntity<ApiResponse<Estado>> crearEstado(@RequestBody Estado estado) {
        try {
            // Guarda el nuevo estado
            Estado nuevoEstado = estadoService.guardarEstado(estado);

            // Devuelve una respuesta exitosa con el nuevo estado
            ApiResponse<Estado> response = new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Estado creado correctamente",
                    true,
                    nuevoEstado
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir
            ApiResponse<Estado> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al crear estado: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Estado")
    public ResponseEntity<ApiResponse<Estado>> actualizarEstado(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            // Verifica si el estado con el ID proporcionado existe
            Optional<Estado> estadoExistente = estadoService.obtenerEstadosPorId(id);

            if (estadoExistente.isEmpty()) {
                // Devuelve un error 404 si el estado no se encuentra
                ApiResponse<Estado> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Estado no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Establece el ID del estado para asegurarse de que se actualice el correcto
            estado.setId(id);

            // Guarda el estado actualizado
            Estado estadoActualizado = estadoService.guardarEstado(estado);

            // Devuelve una respuesta exitosa con el estado actualizado
            ApiResponse<Estado> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Estado actualizado correctamente",
                    true,
                    estadoActualizado
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir
            ApiResponse<Estado> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al actualizar estado: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Estado")
    public ResponseEntity<ApiResponse<Void>> eliminarEstado(@PathVariable Long id) {
        try {
            // Verifica si el estado con el ID proporcionado existe
            Optional<Estado> estadoExistente = estadoService.obtenerEstadosPorId(id);

            if (estadoExistente.isEmpty()) {
                // Devuelve un error 404 si el estado no se encuentra
                ApiResponse<Void> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Estado no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Elimina el estado
            estadoService.eliminarEstado(id);

            // Devuelve una respuesta exitosa con un mensaje
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.NO_CONTENT.value(),
                    "Estado eliminado correctamente",
                    true,
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);

        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al eliminar estado: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
