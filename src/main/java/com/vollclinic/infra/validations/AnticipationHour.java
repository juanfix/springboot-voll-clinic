package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipationHour implements AppointmentValidator {
    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentHour= dataScheduleAppointment.date();

        if(Duration.between(now,appointmentHour).toMinutes() < 30){
            throw new ValidationException("The appointment must be programmed with 30 minutes of anticipation.");
        }
    }
}
