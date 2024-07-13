package com.rapidsecure.rapidsecure.controller;

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


    @GetMapping
    @Operation(summary = "Obtenr", description = "Obtner Usuarios Disponibles")
    public List<Persona> obtenerPersonas(){
        return  personaService.obtenerTodaslasPersonas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Persona por Id")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Persona> persona = personaService.obtenerPersonaPorCedula(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear", description = "Crear Usuario")
    public ResponseEntity<Persona> crearPersona(@RequestBody PersonaDTO personaDTO) {
        Persona nuevaPersona = personaService.guardarPersona(personaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Usuario por Cedula")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        if (!personaService.obtenerPersonaPorCedula(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personaDTO.setId(id);  // asegura que se actualiza el producto correcto
        Persona personaActualizado = personaService.guardarPersona(personaDTO);
        return ResponseEntity.ok(personaActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Usuario por Cedula")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        if (!personaService.obtenerPersonaPorCedula(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }



}
