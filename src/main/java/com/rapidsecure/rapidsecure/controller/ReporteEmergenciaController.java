package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.ReporteEmergenciaDTO;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.mapper.ReporteEmergenciaMapper;
import com.rapidsecure.rapidsecure.service.ReporteEmergenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reporteEmergencia")
@Tag(name = "Reporte Emergencia", description = "Reporte Emergencia")
public class ReporteEmergenciaController {

    @Autowired
    private ReporteEmergenciaService reporteEmergenciaService;

    @Autowired
    private ReporteEmergenciaMapper reporteEmergenciaMapper;

    @GetMapping
    @Operation(summary = "Obtener", description = "Obtener Reporte Emergencia Disponibles")
    public List<ReporteEmergenciaDTO> obtenerTodosLosReporteEmergencia() {
        return reporteEmergenciaService.obtenerReporteEmergencia()
                .stream()
                .map(reporteEmergenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Reporte Emergencia por Id")
    public ResponseEntity<ReporteEmergenciaDTO> obtenerReporteEmergenciaPorId(@PathVariable Long id) {
        Optional<ReporteEmergencia> reporteEmergencia = reporteEmergenciaService.obtenerReporteEmergenciaPorId(id);
        return reporteEmergencia.map(re -> ResponseEntity.ok(reporteEmergenciaMapper.toDto(re)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear", description = "Crear Reporte Emergencia")
    public ResponseEntity<ReporteEmergenciaDTO> crearReporteEmergencia(@RequestBody ReporteEmergenciaDTO reporteEmergenciaDTO) {
        try {
            ReporteEmergencia reporteEmergencia = reporteEmergenciaMapper.toEntity(reporteEmergenciaDTO);
            ReporteEmergencia nuevoReporteEmergencia = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(reporteEmergenciaMapper.toDto(nuevoReporteEmergencia));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Reporte Emergencia")
    public ResponseEntity<ReporteEmergenciaDTO> actualizarReporteEmergencia(@PathVariable Long id, @RequestBody ReporteEmergenciaDTO reporteEmergenciaDTO) {
        if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            ReporteEmergencia reporteEmergencia = reporteEmergenciaMapper.toEntity(reporteEmergenciaDTO);
            reporteEmergencia.setId(id);  // asegura que se actualiza el reporte correcto
            ReporteEmergencia reporteEmergenciaActualizado = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
            return ResponseEntity.ok(reporteEmergenciaMapper.toDto(reporteEmergenciaActualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Reporte Emergencia")
    public ResponseEntity<Void> eliminarReporteEmergencia(@PathVariable Long id) {
        if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteEmergenciaService.eliminarReporteEmergencia(id);
        return ResponseEntity.noContent().build();
    }
}
