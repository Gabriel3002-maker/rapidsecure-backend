package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.dto.*;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.mapper.ReporteEmergenciaMapper;
import com.rapidsecure.rapidsecure.service.ReporteEmergenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reporteEmergencia")
@Tag(name = "Reporte Emergencia", description = "Gestión de Reportes de Emergencia")
public class ReporteEmergenciaController {

    @Autowired
    private ReporteEmergenciaService reporteEmergenciaService;

    @Autowired
    private ReporteEmergenciaMapper reporteEmergenciaMapper;

    @GetMapping
    @Operation(summary = "Obtener todos los reportes", description = "Obtiene todos los reportes de emergencia con estado_id igual a 1")
    public ResponseEntity<ApiResponse<List<ReporteEmergenciaDTO>>> obtenerTodosLosReporteEmergencia() {
        try {
            List<ReporteEmergenciaDTO> reportes = reporteEmergenciaService.obtenerReporteEmergenciaNuevo()
                    .stream()
                    .map(reporteEmergenciaMapper::toDto)
                    .collect(Collectors.toList());

            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Reportes de emergencia obtenidos correctamente",
                    true,
                    reportes
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener reportes de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reporte por ID", description = "Obtiene un reporte de emergencia por su ID")
    public ResponseEntity<ApiResponse<ReporteEmergenciaDTO>> obtenerReporteEmergenciaPorId(@PathVariable Long id) {
        try {
            Optional<ReporteEmergencia> reporteEmergencia = reporteEmergenciaService.obtenerReporteEmergenciaPorId(id);

            if (reporteEmergencia.isPresent()) {
                ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Reporte de emergencia encontrado",
                        true,
                        reporteEmergenciaMapper.toDto(reporteEmergencia.get())
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Reporte de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    @Operation(summary = "Crear nuevo reporte", description = "Crea un nuevo reporte de emergencia")
    public ResponseEntity<ApiResponse<ReporteEmergenciaDTO>> crearReporteEmergencia(@RequestBody ReporteEmergenciaDTO reporteEmergenciaDTO) {
        try {
            ReporteEmergencia reporteEmergencia = reporteEmergenciaMapper.toEntity(reporteEmergenciaDTO);
            ReporteEmergencia nuevoReporteEmergencia = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Reporte de emergencia creado correctamente",
                    true,
                    reporteEmergenciaMapper.toDto(nuevoReporteEmergencia)
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error en los datos del reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al crear reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reporte", description = "Actualiza un reporte de emergencia existente")
    public ResponseEntity<ApiResponse<ReporteEmergenciaDTO>> actualizarReporteEmergencia(@PathVariable Long id, @RequestBody ReporteEmergenciaDTO reporteEmergenciaDTO) {
        try {
            if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
                ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Reporte de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ReporteEmergencia reporteEmergencia = reporteEmergenciaMapper.toEntity(reporteEmergenciaDTO);
            reporteEmergencia.setId(id);  // asegura que se actualiza el reporte correcto
            ReporteEmergencia reporteEmergenciaActualizado = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Reporte de emergencia actualizado correctamente",
                    true,
                    reporteEmergenciaMapper.toDto(reporteEmergenciaActualizado)
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error en los datos del reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ApiResponse<ReporteEmergenciaDTO> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al actualizar reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reporte", description = "Elimina un reporte de emergencia por su ID")
    public ResponseEntity<ApiResponse<Void>> eliminarReporteEmergencia(@PathVariable Long id) {
        try {
            if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
                ApiResponse<Void> response = new ApiResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        "Reporte de emergencia no encontrado",
                        false,
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            reporteEmergenciaService.eliminarReporteEmergencia(id);
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.NO_CONTENT.value(),
                    "Reporte de emergencia eliminado correctamente",
                    true,
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al eliminar reporte de emergencia: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/emergencias/conteo")
    @Operation(summary = "Contar emergencias", description = "Obtiene el conteo de emergencias según estado y fechas")
    public EmergencyCountResponse getEmergencyAttendanceCount(
            @RequestParam Integer estadoId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        // Convert LocalDate to LocalDateTime, start of the day and end of the day
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.plusDays(1).atStartOfDay().minusSeconds(1);

        Long count = reporteEmergenciaService.obtenerConteoEmergencias(estadoId, start, end);

        return new EmergencyCountResponse(count);
    }

    @GetMapping("/reportes")
    @Operation(summary = "Buscar reportes", description = "Busca reportes de emergencia según criterios")
    public ResponseEntity<ApiResponse<List<ReporteEmergenciaResponseDTO>>> buscarReportes(
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchEstado,
            @RequestParam(required = false) String searchTipoEmergencia,
            @RequestParam(required = false) Integer searchPersonaId) {

        List<ReporteEmergenciaResponseDTO> reportes = reporteEmergenciaService.buscarReportes(searchName, searchEstado, searchTipoEmergencia, searchPersonaId);

        ApiResponse<List<ReporteEmergenciaResponseDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Reportes de emergencia encontrados",
                true,
                reportes
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/nuevo")
    @Operation(summary = "Obtener reportes nuevos", description = "Obtiene los reportes de emergencia con estado 'Nuevo'")
    public ResponseEntity<ApiResponse<List<ReporteEmergenciaDTO>>> obtenerReportesNuevos() {
        try {
            List<ReporteEmergenciaDTO> reportes = reporteEmergenciaService.obtenerReporteEmergenciaNuevo()
                    .stream()
                    .map(reporteEmergenciaMapper::toDto)
                    .collect(Collectors.toList());

            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Reportes nuevos obtenidos correctamente",
                    true,
                    reportes
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener reportes nuevos: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/estado/pendiente")
    @Operation(summary = "Obtener reportes pendientes", description = "Obtiene los reportes de emergencia con estado 'Pendiente'")
    public ResponseEntity<ApiResponse<List<ReporteEmergenciaDTO>>> obtenerReportesPendientes() {
        try {
            List<ReporteEmergenciaDTO> reportes = reporteEmergenciaService.obtenerReporteEmergenciaPendientes()
                    .stream()
                    .map(reporteEmergenciaMapper::toDto)
                    .collect(Collectors.toList());

            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Reportes pendientes obtenidos correctamente",
                    true,
                    reportes
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener reportes pendientes: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/estado/finalizado")
    @Operation(summary = "Obtener reportes finalizados", description = "Obtiene los reportes de emergencia con estado 'Finalizado'")
    public ResponseEntity<ApiResponse<List<ReporteEmergenciaDTO>>> obtenerReportesFinalizados() {
        try {
            List<ReporteEmergenciaDTO> reportes = reporteEmergenciaService.obtenerReporteEmergenciaFinalizado()
                    .stream()
                    .map(reporteEmergenciaMapper::toDto)
                    .collect(Collectors.toList());

            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Reportes finalizados obtenidos correctamente",
                    true,
                    reportes
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<ReporteEmergenciaDTO>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al obtener reportes finalizados: " + e.getMessage(),
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
