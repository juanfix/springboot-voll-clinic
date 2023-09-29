package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;

public interface AppointmentValidator {
    public void validate(DataScheduleAppointment dataScheduleAppointment);
}
