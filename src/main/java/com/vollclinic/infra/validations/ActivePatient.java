package com.vollclinic.infra.validations;

import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements AppointmentValidator {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public void validate(DataScheduleAppointment dataScheduleAppointment) {
        if(dataScheduleAppointment.idPatient() == null){
            return;
        }

        boolean activePatient = patientRepository.findActiveById(dataScheduleAppointment.idPatient());

        if(!activePatient){
            throw new ValidationException("You can not schedule any appointments with inactive patients");
        }
    }
}
