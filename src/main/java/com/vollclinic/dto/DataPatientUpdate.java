package com.vollclinic.dto;

import jakarta.validation.constraints.NotNull;

public record DataPatientUpdate(@NotNull Long id, String name, String dni, DataAddress address) {
}
