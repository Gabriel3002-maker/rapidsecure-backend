package com.rapidsecure.rapidsecure.dto;

public class LoginResponseDTO {
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private Long rolId;
    private String token; // Nuevo campo para el token JWT

    // Constructor
    public LoginResponseDTO(Long id, String nombre, String cedula, String correo, String telefono, Long rolId, String token) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.rolId = rolId;
        this.token = token; // Inicializar el token
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
