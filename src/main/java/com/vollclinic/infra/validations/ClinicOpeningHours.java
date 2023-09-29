package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHours implements AppointmentValidator {
    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        Boolean isSunday = DayOfWeek.SUNDAY.equals(dataScheduleAppointment.date().getDayOfWeek());

        Boolean beforeOpening = dataScheduleAppointment.date().getHour()<7;
        Boolean afterClosing = dataScheduleAppointment.date().getHour()>19;

        if(isSunday || beforeOpening || afterClosing){
            throw  new ValidationException("Clinic opening hours is from 07:00 to 19:00 hours");
        }

    }
}
