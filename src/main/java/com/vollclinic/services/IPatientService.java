package com.vollclinic.services;

import com.vollclinic.dto.DataPatient;
import com.vollclinic.dto.DataPatientList;
import com.vollclinic.dto.DataPatientRegister;
import com.vollclinic.dto.DataPatientUpdate;
import com.vollclinic.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IPatientService {
    public ResponseEntity<Page<DataPatientList>> getAll(Pageable pagination);

    public ResponseEntity<Patient> getById(Long id);

    public ResponseEntity<DataPatient> createPatient(DataPatientRegister dataPatientRegister, UriComponentsBuilder uriComponentsBuilder);

    public ResponseEntity updatePatient(DataPatientUpdate dataPatientUpdate);

    public ResponseEntity deactivatePatient(Long id);

    public ResponseEntity<Page<DataPatientList>> searchByCriteria(DataPatientList dataPatientList, Pageable pagination);
}
