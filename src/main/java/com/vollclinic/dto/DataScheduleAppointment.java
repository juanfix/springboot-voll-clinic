package com.vollclinic.dto;

import com.vollclinic.enums.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataScheduleAppointment(
        @NotNull
        Long idPatient,
        Long idDoctor,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty) {

}
