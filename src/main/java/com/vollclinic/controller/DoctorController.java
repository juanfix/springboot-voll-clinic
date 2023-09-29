package com.vollclinic.controller;

import com.vollclinic.dto.DataDoctor;
import com.vollclinic.dto.DataDoctorList;
import com.vollclinic.dto.DataDoctorRegister;
import com.vollclinic.dto.DataDoctorUpdate;
import com.vollclinic.models.Doctor;
import com.vollclinic.services.IDoctorService;
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

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<Page<DataDoctorList>> getAll(@PageableDefault(size = 10) Pageable pagination) {
        return doctorService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getOne(@PathVariable Long id) {
        return doctorService.getById(id);
    }

    @PostMapping
    public ResponseEntity<DataDoctor> createDoctor(@RequestBody @Valid DataDoctorRegister dataDoctorRegister, UriComponentsBuilder uriComponentsBuilder) {
        return doctorService.createDoctor(dataDoctorRegister, uriComponentsBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataDoctorUpdate dataDoctorUpdate) {
        return doctorService.updateDoctor(dataDoctorUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivateDoctor(@PathVariable Long id) {
        return doctorService.deactivateDoctor(id);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DataDoctorList>> searchByCriteria(@RequestBody DataDoctorList dataDoctorList, @PageableDefault(size = 10) Pageable pagination) {
        return doctorService.searchByCriteria(dataDoctorList, pagination);
    }

}
