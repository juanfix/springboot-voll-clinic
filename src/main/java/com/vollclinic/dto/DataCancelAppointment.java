package com.vollclinic.dto;

import com.vollclinic.enums.CancellationReason;
import jakarta.validation.constraints.NotNull;

public record DataCancelAppointment(
        @NotNull
        Long idAppointment,
        CancellationReason cancellationReason) {
}
