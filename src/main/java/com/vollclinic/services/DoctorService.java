package com.vollclinic.services;

import com.vollclinic.dto.*;
import com.vollclinic.models.Doctor;
import com.vollclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ResponseEntity<Page<DataDoctorList>> getAll(Pageable pagination) {
        return ResponseEntity.ok(doctorRepository.findByIsActiveTrue(pagination).map(DataDoctorList::new));
    }

    @Override
    public ResponseEntity<Doctor> getById(Long id) {
        return ResponseEntity.ok(doctorRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<DataDoctor> createDoctor(DataDoctorRegister dataDoctorRegister, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = doctorRepository.save(new Doctor(dataDoctorRegister));
        DataDoctor dataDoctor = new DataDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(),
                doctor.getPhone(), doctor.getSpecialty().toString(),
                new DataAddress(doctor.getAddress().getAddress(), doctor.getAddress().getCounty(),
                        doctor.getAddress().getZipcode(), doctor.getAddress().getComplement(),
                        doctor.getAddress().getCity()));

        URI url = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(dataDoctor);
    }

    @Override
    public ResponseEntity updateDoctor(DataDoctorUpdate dataDoctorUpdate) {
        Doctor doctor = doctorRepository.getReferenceById(dataDoctorUpdate.id());
        doctor.updateData(dataDoctorUpdate);
        return ResponseEntity.ok(new DataDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(),
                doctor.getPhone(), doctor.getSpecialty().toString(),
                new DataAddress(doctor.getAddress().getAddress(), doctor.getAddress().getCounty(),
                        doctor.getAddress().getZipcode(), doctor.getAddress().getComplement(),
                        doctor.getAddress().getCity())));
    }

    @Override
    public ResponseEntity deactivateDoctor(Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<DataDoctorList>> searchByCriteria(DataDoctorList dataDoctorList, Pageable pagination) {
        return ResponseEntity.ok(doctorRepository.searchByCriteria(dataDoctorList.name(), dataDoctorList.email(), dataDoctorList.dni(), dataDoctorList.specialty(), pagination));
    }
}
