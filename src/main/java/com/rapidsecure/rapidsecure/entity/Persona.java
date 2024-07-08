package com.rapidsecure.rapidsecure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación Many-to-One
    @JoinColumn(name = "rol_id")        // Nombre de la columna en la tabla persona que guarda la clave foránea
    private Rol rol;                     // Objeto Rol que representa el rol de la persona

    private String password;

    // Constructor por defecto (necesario para JPA)
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombre, String cedula, String correo, String telefono, Rol rol, String password) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.password = password;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}