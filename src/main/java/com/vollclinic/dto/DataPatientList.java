package com.vollclinic.dto;

import com.vollclinic.models.Patient;

public record DataPatientList(Long id, String name, String email, String dni) {

    public DataPatientList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getDni());
    }
}
