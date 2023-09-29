package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataCancelAppointment;
import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.models.Appointment;
import com.vollclinic.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelAnticipationHour implements CancelAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public void validate(DataCancelAppointment dataCancelAppointment) {
        Appointment appointment = appointmentRepository.getReferenceById(dataCancelAppointment.idAppointment());
        LocalDateTime now = LocalDateTime.now();
        long differenceInHours= Duration.between(now, appointment.getDate()).toHours();

        if(differenceInHours < 24){
            throw new ValidationException("The appointment can only be canceled with a minimum notice of 24 hours.");
        }
    }
}
