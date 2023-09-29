package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAppointment implements AppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        if(dataScheduleAppointment.idDoctor() == null)
            return;

        boolean doctorWithAppointment= appointmentRepository.existsByDoctorIdAndDate(dataScheduleAppointment.idDoctor(),dataScheduleAppointment.date());
        if(doctorWithAppointment){
            throw new ValidationException("This doctor already has an appointment on that date.");
        }
    }
}
