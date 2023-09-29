package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataCancelAppointment;

public interface CancelAppointmentValidator {
    public void validate(DataCancelAppointment dataCancelAppointment);
}
