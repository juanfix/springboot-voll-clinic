package com.vollclinic.dto;

import com.vollclinic.models.Appointment;

import java.time.LocalDateTime;

public record DataAppointmentList(Long id, Long idPatient, Long idDoctor, LocalDateTime date) {
    public DataAppointmentList(Appointment appointment) {
        this(appointment.getId(),appointment.getPatient().getId(),appointment.getDoctor().getId(),appointment.getDate());
    }
}
