package com.vollclinic.dto;

import com.vollclinic.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataDoctorRegister(

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
        Specialty specialty,
        @NotNull
        @Valid
        DataAddress address) {
}
