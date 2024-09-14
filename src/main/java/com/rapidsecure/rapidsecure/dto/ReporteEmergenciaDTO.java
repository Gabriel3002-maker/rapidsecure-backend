package com.rapidsecure.rapidsecure.dto;

import java.time.LocalDateTime;

public class ReporteEmergenciaDTO {
    private Long id;
    private Long personaId;
    private LocalDateTime fechaHoraReporte;
    private String ubicacion;
    private String descripcion;
    private Long estadoId;
    private Double latitud;
    private Double longitud;
    private Long tipoEmergenciaId;
    private Long usuarioAtendioId;  // ID del usuario que atendió la emergencia
    private LocalDateTime horaAtencion;  // Hora en que se tomó la emergencia

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
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

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
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

    public Long getTipoEmergenciaId() {
        return tipoEmergenciaId;
    }

    public void setTipoEmergenciaId(Long tipoEmergenciaId) {
        this.tipoEmergenciaId = tipoEmergenciaId;
    }

    public Long getUsuarioAtendioId() {
        return usuarioAtendioId;
    }

    public void setUsuarioAtendioId(Long usuarioAtendioId) {
        this.usuarioAtendioId = usuarioAtendioId;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
}
