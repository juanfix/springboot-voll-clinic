package com.vollclinic.services;

import com.vollclinic.dto.*;
import com.vollclinic.infra.errors.IntegrityValidation;
import com.vollclinic.infra.validations.AppointmentValidator;
import com.vollclinic.infra.validations.CancelAppointmentValidator;
import com.vollclinic.models.Address;
import com.vollclinic.models.Appointment;
import com.vollclinic.models.Doctor;
import com.vollclinic.models.Patient;
import com.vollclinic.repository.AppointmentRepository;
import com.vollclinic.repository.DoctorRepository;
import com.vollclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<AppointmentValidator> validatorList;

    @Autowired
    private List<CancelAppointmentValidator> cancelValidatorList;

    @Override
    public ResponseEntity<Appointment> getById(Long id) { return ResponseEntity.ok(appointmentRepository.findById(id).get()); }

    @Override
    public ResponseEntity<DataAppointment> createAppointment(DataScheduleAppointment dataScheduleAppointment, UriComponentsBuilder uriComponentsBuilder) {

        if(patientRepository.findById(dataScheduleAppointment.idPatient()).isEmpty()){
            throw new IntegrityValidation("This patient ID does not was found");
        }

        if(dataScheduleAppointment.idDoctor() != null && !doctorRepository.existsById(dataScheduleAppointment.idDoctor())){
            throw new IntegrityValidation("This doctor ID does not was found");
        }

        validatorList.forEach(v-> v.validate(dataScheduleAppointment));

        Patient patient = patientRepository.findById(dataScheduleAppointment.idPatient()).get();
        Doctor doctor = selectDoctor(dataScheduleAppointment);

        if(doctor == null){
            throw new IntegrityValidation("The Schedule for that hour is not available / specialty does not exist");
        }

        Appointment appointment = new Appointment(null,doctor,patient,dataScheduleAppointment.date(), null);

        appointmentRepository.save(appointment);

        DataAppointment dataAppointment = new DataAppointment(appointment.getId(),
                new Patient(
                        appointment.getPatient().getId(), appointment.getPatient().getName(), appointment.getPatient().getEmail(),
                        appointment.getPatient().getPhone(), appointment.getPatient().getDni(), appointment.getPatient().getIsActive(),
                        new Address(appointment.getPatient().getAddress().getAddress(),
                                appointment.getPatient().getAddress().getCounty(),
                                appointment.getPatient().getAddress().getZipcode(),
                                appointment.getPatient().getAddress().getComplement(),
                                appointment.getPatient().getAddress().getCity())
                )
                ,
                new Doctor(
                        appointment.getDoctor().getId(), appointment.getDoctor().getName(), appointment.getDoctor().getEmail(),
                        appointment.getDoctor().getPhone(), appointment.getDoctor().getDni(), appointment.getDoctor().getIsActive(),
                        appointment.getDoctor().getSpecialty(),
                        new Address(appointment.getDoctor().getAddress().getAddress(),
                                appointment.getDoctor().getAddress().getCounty(),
                                appointment.getDoctor().getAddress().getZipcode(),
                                appointment.getDoctor().getAddress().getComplement(),
                                appointment.getDoctor().getAddress().getCity())
                ),
                appointment.getDate());
        URI url = uriComponentsBuilder.path("/appointment/{id}").buildAndExpand(appointment.getId()).toUri();

        return ResponseEntity.created(url).body(dataAppointment);
    }

    @Override
    public ResponseEntity cancelAppointment(DataCancelAppointment dataCancelAppointment) {
        if(!appointmentRepository.existsById(dataCancelAppointment.idAppointment())){
            throw new IntegrityValidation("This appointment does not exist");
        }

        cancelValidatorList.forEach(v-> v.validate(dataCancelAppointment));

        Appointment appointment = appointmentRepository.getReferenceById(dataCancelAppointment.idAppointment());
        appointment.cancelAppointment(dataCancelAppointment.cancellationReason());
        return ResponseEntity.noContent().build();
    }

    private Doctor selectDoctor(DataScheduleAppointment dataScheduleAppointment) {
        if(dataScheduleAppointment.idDoctor() != null){
            return doctorRepository.getReferenceById(dataScheduleAppointment.idDoctor());
        }
        if(dataScheduleAppointment.specialty() == null){
            throw new IntegrityValidation("You must select a specialty for a doctor");
        }

        return doctorRepository.selectDoctorWithSpecialtyOnDate(dataScheduleAppointment.specialty(), dataScheduleAppointment.date());
    }
}
