package com.rapidsecure.rapidsecure.controller;

import com.rapidsecure.rapidsecure.service.EmergencyCountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/emergency-graphics")
@Tag(name= "Graphics" , description = "Graphics")
public class EmergencyCountController {

    @Autowired
    private EmergencyCountService emergencyCountService;

    @GetMapping("/emergency-counts")
    public List<Map<String, Object>> getEmergencyCounts(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return emergencyCountService.getEmergencyCounts(startDate, endDate);
    }

    @GetMapping("/emergency-zones")
    public List<Map<String, Object>> getEmergencyZone(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer tipoemergenciaId

            ) {
        return emergencyCountService.getEmergencyZone(startDate, endDate, tipoemergenciaId);
    }
}
