package com.rapidsecure.rapidsecure.dto;

public class EmergencyCountResponse {
    private Long totalEmergency;

    public EmergencyCountResponse(Long totalEmergency) {
        this.totalEmergency = totalEmergency;
    }

    public Long getTotalEmergency() {
        return totalEmergency;
    }

    public void setTotalEmergency(Long totalEmergency) {
        this.totalEmergency = totalEmergency;
    }
}
