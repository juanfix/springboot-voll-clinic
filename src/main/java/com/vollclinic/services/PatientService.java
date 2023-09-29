package com.vollclinic.services;

import com.vollclinic.dto.*;
import com.vollclinic.models.Patient;
import com.vollclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResponseEntity<Page<DataPatientList>> getAll(Pageable pagination) {
        return ResponseEntity.ok(patientRepository.findByIsActiveTrue(pagination).map(DataPatientList::new));
    }

    @Override
    public ResponseEntity<Patient> getById(Long id) {
        return ResponseEntity.ok(patientRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<DataPatient> createPatient(DataPatientRegister dataPatientRegister, UriComponentsBuilder uriComponentsBuilder) {
        Patient patient = patientRepository.save(new Patient(dataPatientRegister));
        DataPatient dataPatient = new DataPatient(patient.getId(), patient.getName(), patient.getEmail(),
                patient.getPhone(), patient.getDni(),
                new DataAddress(patient.getAddress().getAddress(), patient.getAddress().getCounty(),
                        patient.getAddress().getZipcode(), patient.getAddress().getComplement(),
                        patient.getAddress().getCity()));

        URI url = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(dataPatient);
    }

    @Override
    public ResponseEntity updatePatient(DataPatientUpdate dataPatientUpdate) {
        Patient patient = patientRepository.getReferenceById(dataPatientUpdate.id());
        patient.updateData(dataPatientUpdate);
        return ResponseEntity.ok(new DataPatient(patient.getId(), patient.getName(), patient.getEmail(),
                patient.getPhone(), patient.getDni(),
                new DataAddress(patient.getAddress().getAddress(), patient.getAddress().getCounty(),
                        patient.getAddress().getZipcode(), patient.getAddress().getComplement(),
                        patient.getAddress().getCity())));
    }

    @Override
    public ResponseEntity deactivatePatient(Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<DataPatientList>> searchByCriteria(DataPatientList dataPatientList, Pageable pagination) {
        return ResponseEntity.ok(patientRepository.searchByCriteria(dataPatientList.name(), dataPatientList.email(), dataPatientList.dni(), pagination));
    }
}
