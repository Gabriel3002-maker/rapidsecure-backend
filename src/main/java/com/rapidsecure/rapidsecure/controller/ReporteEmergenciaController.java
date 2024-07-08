package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.entity.Estado;
import com.rapidsecure.rapidsecure.entity.ReporteEmergencia;
import com.rapidsecure.rapidsecure.service.ReporteEmergenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reporteEmergencia")
@Tag(name= "Reporte Emergencia" , description = "Reporte Emergencia")
public class ReporteEmergenciaController {
    @Autowired
    private ReporteEmergenciaService reporteEmergenciaService;

    @GetMapping
    @Operation(summary = "Obtener", description = "Obtener  Reporte Emergencia Disponibles")
    public List<ReporteEmergencia> obtenerTodosLosReporteEmergencia(){
        return  reporteEmergenciaService.obtenerReporteEmergencia();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener", description = "Obtener Reporte Emergencia por Id")
    public ResponseEntity<ReporteEmergencia> obtenerReporteEmergenciaPorId(@PathVariable Long id) {
        Optional<ReporteEmergencia> reporteEmergencia = reporteEmergenciaService.obtenerReporteEmergenciaPorId(id);
        return reporteEmergencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear", description = "Crear Reporte Emergencia")
    public ResponseEntity<ReporteEmergencia> crearReporteEmergencia(@RequestBody ReporteEmergencia reporteEmergencia) {
        ReporteEmergencia nuevoReporteEmergencia = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporteEmergencia);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", description = "Actualizar Reporte Emergencia")
    public ResponseEntity<ReporteEmergencia> actualizarReporteEmergencia(@PathVariable Long id, @RequestBody  ReporteEmergencia reporteEmergencia) {
        if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteEmergencia.setId(id);  // asegura que se actualiza el producto correcto
        ReporteEmergencia reporteEmergenciaActualizado = reporteEmergenciaService.guardarEmergencia(reporteEmergencia);
        return ResponseEntity.ok(reporteEmergenciaActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Eliminar Reporte Emergencia ")
    public ResponseEntity<Void> eliminarReporteEmergencia(@PathVariable Long id) {
        if (!reporteEmergenciaService.obtenerReporteEmergenciaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteEmergenciaService.eliminarReporteEmergencia(id);
        return ResponseEntity.noContent().build();
    }

}
