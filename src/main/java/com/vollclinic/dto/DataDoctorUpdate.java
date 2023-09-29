package com.vollclinic.dto;

import jakarta.validation.constraints.NotNull;

public record DataDoctorUpdate(@NotNull Long id, String name, String dni, DataAddress address) {
}
