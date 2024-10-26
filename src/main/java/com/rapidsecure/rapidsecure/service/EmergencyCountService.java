package com.rapidsecure.rapidsecure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmergencyCountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getEmergencyCounts(String startDate, String endDate) {
        String sql = "SELECT * FROM get_emergency_counts(CAST(? AS DATE), CAST(? AS DATE))";
        return jdbcTemplate.queryForList(sql, startDate, endDate);
    }

    
    public List<Map<String, Object>> getEmergencyZone(String startDate, String endDate , Integer tipoemergenciaId) {
    String sql = "SELECT latitud, longitud " +
                 "FROM reporte_emergencia re " +
                 "WHERE fecha_hora_reporte >= ? " +
                 "AND fecha_hora_reporte <= ? " +
                 "AND tipo_emergencia_id = ?";

    return jdbcTemplate.queryForList(sql, startDate, endDate, tipoemergenciaId );
}




}
