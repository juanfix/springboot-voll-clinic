package com.vollclinic.dto;

import com.vollclinic.models.Doctor;
import com.vollclinic.models.Patient;

import java.time.LocalDateTime;

public record DataAppointment(Long id, Patient patient, Doctor doctor, LocalDateTime date) {
}
