package com.rapidsecure.rapidsecure.mapper;

import com.rapidsecure.rapidsecure.dto.ReporteEmergenciaDTO;
import com.rapidsecure.rapidsecure.entity.*;
import com.rapidsecure.rapidsecure.service.EstadoService;
import com.rapidsecure.rapidsecure.service.PersonaService;
import com.rapidsecure.rapidsecure.service.TipoEmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReporteEmergenciaMapper {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private TipoEmergenciaService tipoEmergenciaService;

    public ReporteEmergenciaDTO toDto(ReporteEmergencia entity) {
        ReporteEmergenciaDTO dto = new ReporteEmergenciaDTO();
        dto.setId(entity.getId());
        dto.setPersonaId(entity.getPersona().getId());
        dto.setFechaHoraReporte(entity.getFechaHoraReporte());
        dto.setUbicacion(entity.getUbicacion());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEstadoId(entity.getEstado().getId());
        dto.setLatitud(entity.getLatitud());
        dto.setLongitud(entity.getLongitud());
        dto.setTipoEmergenciaId(entity.getTipoEmergencia().getId());

        // Nuevos campos opcionales
        if (entity.getUsuarioAtendio() != null) {
            dto.setUsuarioAtendioId(entity.getUsuarioAtendio().getId());
        }
        dto.setHoraAtencion(entity.getHoraAtencion());

        return dto;
    }

    public ReporteEmergencia toEntity(ReporteEmergenciaDTO dto) {
        ReporteEmergencia entity = new ReporteEmergencia();
        entity.setId(dto.getId());
        entity.setFechaHoraReporte(dto.getFechaHoraReporte());
        entity.setUbicacion(dto.getUbicacion());
        entity.setDescripcion(dto.getDescripcion());

        // Inicializar las entidades relacionadas
        Persona persona = personaService.obtenerPersonaPorCedula(dto.getPersonaId())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
        Estado estado = estadoService.obtenerEstadosPorId(dto.getEstadoId())
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));
        TipoEmergencia tipoEmergencia = tipoEmergenciaService.obtenerTipoEmergenciaPorId(dto.getTipoEmergenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Emergencia no encontrado"));

        entity.setPersona(persona);
        entity.setEstado(estado);
        entity.setTipoEmergencia(tipoEmergencia);
        entity.setLatitud(dto.getLatitud());
        entity.setLongitud(dto.getLongitud());

        // Asignar campos opcionales si están presentes
        if (dto.getUsuarioAtendioId() != null) {
            Persona usuarioAtendio = personaService.obtenerPersonaPorCedula(dto.getUsuarioAtendioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            entity.setUsuarioAtendio(usuarioAtendio);
        }
        entity.setHoraAtencion(dto.getHoraAtencion());

        return entity;
    }
}
