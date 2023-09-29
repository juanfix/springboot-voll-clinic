package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctor implements AppointmentValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        if(dataScheduleAppointment.idDoctor() == null){
            return;
        }

        Boolean activeDoctor = doctorRepository.findActiveById(dataScheduleAppointment.idDoctor());

        if(!activeDoctor){
            throw new ValidationException("You can not schedule any appointments with inactive doctors");
        }
    }
}
