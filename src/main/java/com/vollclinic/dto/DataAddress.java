package com.vollclinic.dto;

import jakarta.validation.constraints.NotBlank;

public record DataAddress (
        @NotBlank
        String address,
        @NotBlank
        String county,
        @NotBlank
        String zipcode,
        @NotBlank
        String complement,
        @NotBlank
        String city) {
}
