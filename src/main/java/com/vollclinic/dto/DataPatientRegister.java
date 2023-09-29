package com.vollclinic.dto;

import com.vollclinic.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataPatientRegister(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        //@Pattern(regexp = "\\d{4,6}")
        String dni,
        @NotNull
        @Valid
        DataAddress address) {
}
