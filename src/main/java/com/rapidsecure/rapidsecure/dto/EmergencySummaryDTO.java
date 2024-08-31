package com.rapidsecure.rapidsecure.dto;

public class EmergencySummaryDTO {
    private Long emergencyNew;
    private Long emergencyPending;
    private Long emergencyAttendance;

    public EmergencySummaryDTO(Long emergencyNew, Long emergencyPending, Long emergencyAttendance) {
        this.emergencyNew = emergencyNew;
        this.emergencyPending = emergencyPending;
        this.emergencyAttendance = emergencyAttendance;
    }

    // Getters y setters

    public Long getEmergencyNew() {
        return emergencyNew;
    }

    public void setEmergencyNew(Long emergencyNew) {
        this.emergencyNew = emergencyNew;
    }

    public Long getEmergencyPending() {
        return emergencyPending;
    }

    public void setEmergencyPending(Long emergencyPending) {
        this.emergencyPending = emergencyPending;
    }

    public Long getEmergencyAttendance() {
        return emergencyAttendance;
    }

    public void setEmergencyAttendance(Long emergencyAttendance) {
        this.emergencyAttendance = emergencyAttendance;
    }

    @Override
    public String toString() {
        return "EmergencySummaryDTO{" +
                "emergencyNew=" + emergencyNew +
                ", emergencyPending=" + emergencyPending +
                ", emergencyAttendance=" + emergencyAttendance +
                '}';
    }
}
