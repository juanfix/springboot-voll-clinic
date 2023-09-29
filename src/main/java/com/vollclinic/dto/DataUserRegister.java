package com.vollclinic.dto;

import jakarta.validation.constraints.NotNull;

public record DataUserRegister(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
