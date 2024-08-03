package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.ApiResponse;
import com.rapidsecure.rapidsecure.dto.LoginRequestDTO;
import com.rapidsecure.rapidsecure.dto.ResetPasswordRequestDTO;
import com.rapidsecure.rapidsecure.service.PersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@Tag(name = "Login", description = "Login")
public class LoginController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            boolean isValidUser = personaService.verificarLogin(loginRequestDTO.getCorreo(), loginRequestDTO.getPassword());
            if (isValidUser) {
                ApiResponse<String> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Login exitoso",
                        true,
                        "Login exitoso"
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<String> response = new ApiResponse<>(
                        HttpStatus.UNAUTHORIZED.value(),
                        "Credenciales incorrectas",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error en el proceso de login: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        try {
            boolean isReset = personaService.resetPassword(resetPasswordRequestDTO.getCorreo(), resetPasswordRequestDTO.getNuevaPassword());
            if (isReset) {
                ApiResponse<String> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Contraseña actualizada exitosamente",
                        true,
                        "Contraseña actualizada exitosamente"
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<String> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Usuario no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al actualizar la contraseña: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
