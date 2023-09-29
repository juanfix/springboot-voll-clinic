package com.vollclinic.services;

import com.vollclinic.dto.DataDoctor;
import com.vollclinic.dto.DataDoctorList;
import com.vollclinic.dto.DataDoctorRegister;
import com.vollclinic.dto.DataDoctorUpdate;
import com.vollclinic.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IDoctorService {

    public ResponseEntity<Page<DataDoctorList>> getAll(Pageable pagination);

    public ResponseEntity<Doctor> getById(Long id);

    public ResponseEntity<DataDoctor> createDoctor(DataDoctorRegister dataDoctorRegister, UriComponentsBuilder uriComponentsBuilder);

    public ResponseEntity updateDoctor(DataDoctorUpdate dataDoctorUpdate);

    public ResponseEntity deactivateDoctor(Long id);
    public ResponseEntity<Page<DataDoctorList>> searchByCriteria(DataDoctorList dataDoctorList, Pageable pagination);

}
