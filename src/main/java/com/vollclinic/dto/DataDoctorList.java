package com.vollclinic.dto;

import com.vollclinic.enums.Specialty;
import com.vollclinic.models.Doctor;

public record DataDoctorList(Long id, String name, String email, String dni, Specialty specialty) {
    public DataDoctorList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getDni(), doctor.getSpecialty());
    }
}
