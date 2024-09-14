package com.rapidsecure.rapidsecure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporte_emergencia")
public class ReporteEmergencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Column(nullable = false)
    private LocalDateTime fechaHoraReporte;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @ManyToOne
    @JoinColumn(name = "tipo_emergencia_id", nullable = false)
    private TipoEmergencia tipoEmergencia;

    // Nuevas columnas
    @ManyToOne
    @JoinColumn(name = "usuario_atendio_id")
    private Persona usuarioAtendio;  // Relación con Persona para el usuario que atendió la emergencia

    @Column(name = "hora_atencion")
    private LocalDateTime horaAtencion;  // Hora en que se tomó la emergencia

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LocalDateTime getFechaHoraReporte() {
        return fechaHoraReporte;
    }

    public void setFechaHoraReporte(LocalDateTime fechaHoraReporte) {
        this.fechaHoraReporte = fechaHoraReporte;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public TipoEmergencia getTipoEmergencia() {
        return tipoEmergencia;
    }

    public void setTipoEmergencia(TipoEmergencia tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
    }

    public Persona getUsuarioAtendio() {
        return usuarioAtendio;
    }

    public void setUsuarioAtendio(Persona usuarioAtendio) {
        this.usuarioAtendio = usuarioAtendio;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
}
