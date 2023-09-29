package com.vollclinic.repository;

import com.vollclinic.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);

    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);
}
