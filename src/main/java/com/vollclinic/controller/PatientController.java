package com.vollclinic.controller;

import com.vollclinic.dto.*;
import com.vollclinic.models.Doctor;
import com.vollclinic.models.Patient;
import com.vollclinic.services.IPatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    IPatientService patientService;

    @GetMapping
    public ResponseEntity<Page<DataPatientList>> getAll(@PageableDefault(size = 10) Pageable pagination) {
        return patientService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getOne(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @PostMapping
    public ResponseEntity<DataPatient> createDoctor(@RequestBody @Valid DataPatientRegister dataPatientRegister, UriComponentsBuilder uriComponentsBuilder) {
        return patientService.createPatient(dataPatientRegister, uriComponentsBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataPatientUpdate dataPatientUpdate) {
        return patientService.updatePatient(dataPatientUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivatePatient(@PathVariable Long id) {
        return patientService.deactivatePatient(id);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DataPatientList>> searchByCriteria(@RequestBody DataPatientList dataPatientList, @PageableDefault(size = 10) Pageable pagination) {
        return patientService.searchByCriteria(dataPatientList, pagination);
    }
}
