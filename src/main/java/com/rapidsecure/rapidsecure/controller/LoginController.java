package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.LoginRequestDTO;
import com.rapidsecure.rapidsecure.dto.ResetPasswordRequestDTO;
import com.rapidsecure.rapidsecure.service.PersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@Tag(name="Login" , description = "Login")
public class LoginController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        boolean isValidUser = personaService.verificarLogin(loginRequestDTO.getCorreo(), loginRequestDTO.getPassword());
        if (isValidUser) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        boolean isReset = personaService.resetPassword(resetPasswordRequestDTO.getCorreo(), resetPasswordRequestDTO.getNuevaPassword());
        if (isReset) {
            return ResponseEntity.ok("Contraseña actualizada exitosamente");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }
}
