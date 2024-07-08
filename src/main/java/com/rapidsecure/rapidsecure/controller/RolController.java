package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.entity.Persona;
import com.rapidsecure.rapidsecure.entity.Rol;
import com.rapidsecure.rapidsecure.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rol")
@Tag(name="Rol" , description = "Rol")
public class RolController {
    @Autowired
    private RolService rolService;



    @GetMapping
    @Operation(summary = "Obtener", description = "Obtner Roles Disponibles")
    public List<Rol> obtenerRoles(){
        return  rolService.obtenerRoles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Rol por Id")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        Optional<Rol> rol = rolService.obtenerRolPorId(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear", description = "Crear Rol")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.guardarRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body( nuevoRol);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Rol ")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol ) {
        if (!rolService.obtenerRolPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rol.setId(id);  // asegura que se actualiza el producto correcto
        Rol rolActualizado = rolService.guardarRol(rol);
        return ResponseEntity.ok(rolActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Rol ")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        if (!rolService.obtenerRolPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
