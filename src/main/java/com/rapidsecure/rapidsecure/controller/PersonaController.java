package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.ApiResponse;
import com.rapidsecure.rapidsecure.dto.PersonaDTO;
import com.rapidsecure.rapidsecure.entity.Estado;
import com.rapidsecure.rapidsecure.entity.Persona;
import com.rapidsecure.rapidsecure.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@Tag(name="Persona" , description = "Persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;


    /*@GetMapping
    @Operation(summary = "Obtenr", description = "Obtner Usuarios Disponibles")
    public List<Persona> obtenerPersonas(){
        return  personaService.obtenerTodaslasPersonas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Persona por Id")
    public ResponseEntity<ApiResponse<Persona>> obtenerPersonaPorId(@PathVariable Long id) {
        try {
            Optional<Persona> persona = personaService.obtenerPersonaPorCedula(id);
            if (persona.isPresent()) {
                ApiResponse<Persona> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Persona encontrada",
                        true,
                        persona.get()
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Persona> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Persona no encontrada",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Persona> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener persona: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }**/



    @PostMapping
    @Operation(summary = "Crear", description = "Crear Usuario")
    public ResponseEntity<ApiResponse<Persona>> crearPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            Persona nuevaPersona = personaService.guardarPersona(personaDTO);
            ApiResponse<Persona> response = new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Persona creada correctamente",
                    true,
                    nuevaPersona
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Persona> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al crear persona: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /*@PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Usuario por Cedula")
    public ResponseEntity<ApiResponse<Persona>> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        try {
            if (!personaService.obtenerPersonaPorCedula(id).isPresent()) {
                ApiResponse<Persona> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Persona no encontrada",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            personaDTO.setId(id);
            Persona personaActualizada = personaService.guardarPersona(personaDTO);
            ApiResponse<Persona> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Persona actualizada correctamente",
                    true,
                    personaActualizada
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Persona> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al actualizar persona: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }**/


    /*@DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Usuario por Cedula")
    public ResponseEntity<ApiResponse<Void>> eliminarPersona(@PathVariable Long id) {
        try {
            if (!personaService.obtenerPersonaPorCedula(id).isPresent()) {
                ApiResponse<Void> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Persona no encontrada",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            personaService.eliminarPersona(id);
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.NO_CONTENT.value(),
                    "Persona eliminada correctamente",
                    true,
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al eliminar persona: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }**/




}
