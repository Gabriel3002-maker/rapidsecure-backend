package com.rapidsecure.rapidsecure.dto;

public class ReporteEmergenciaResponseDTO {
    private Long reporteId;
    private Long estadoId;
    private String estadoNombre;
    private Long personaId;
    private String personaNombre;
    private Long tipoEmergenciaId;
    private String tipoEmergenciaDescripcion;

    // Constructor
    public ReporteEmergenciaResponseDTO(Long reporteId, Long estadoId, String estadoNombre, Long personaId,
                                String personaNombre, Long tipoEmergenciaId, String tipoEmergenciaDescripcion) {
        this.reporteId = reporteId;
        this.estadoId = estadoId;
        this.estadoNombre = estadoNombre;
        this.personaId = personaId;
        this.personaNombre = personaNombre;
        this.tipoEmergenciaId = tipoEmergenciaId;
        this.tipoEmergenciaDescripcion = tipoEmergenciaDescripcion;
    }

    // Getters y Setters
    public Long getReporteId() { return reporteId; }
    public void setReporteId(Long reporteId) { this.reporteId = reporteId; }

    public Long getEstadoId() { return estadoId; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }

    public String getEstadoNombre() { return estadoNombre; }
    public void setEstadoNombre(String estadoNombre) { this.estadoNombre = estadoNombre; }

    public Long getPersonaId() { return personaId; }
    public void setPersonaId(Long personaId) { this.personaId = personaId; }

    public String getPersonaNombre() { return personaNombre; }
    public void setPersonaNombre(String personaNombre) { this.personaNombre = personaNombre; }

    public Long getTipoEmergenciaId() { return tipoEmergenciaId; }
    public void setTipoEmergenciaId(Long tipoEmergenciaId) { this.tipoEmergenciaId = tipoEmergenciaId; }

    public String getTipoEmergenciaDescripcion() { return tipoEmergenciaDescripcion; }
    public void setTipoEmergenciaDescripcion(String tipoEmergenciaDescripcion) { this.tipoEmergenciaDescripcion = tipoEmergenciaDescripcion; }
}
