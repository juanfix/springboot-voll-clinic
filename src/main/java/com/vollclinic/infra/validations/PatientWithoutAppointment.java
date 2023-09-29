package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientWithoutAppointment implements AppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        LocalDateTime firstHour = dataScheduleAppointment.date().withHour(7);
        LocalDateTime lastHour= dataScheduleAppointment.date().withHour(18);

        boolean patientWithAppointment = appointmentRepository.existsByPatientIdAndDateBetween(dataScheduleAppointment.idPatient(), firstHour, lastHour);

        if(patientWithAppointment){
            throw new ValidationException("This patient already has an appointment on that date.");
        }
    }
}
