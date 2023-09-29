package com.vollclinic.repository;

import com.vollclinic.dto.DataAddress;
import com.vollclinic.dto.DataDoctorRegister;
import com.vollclinic.dto.DataPatientRegister;
import com.vollclinic.enums.Specialty;
import com.vollclinic.models.Appointment;
import com.vollclinic.models.Doctor;
import com.vollclinic.models.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It must be return null when the doctor already have an appointment in that hour")
    void selectDoctorWithSpecialtyOnDateScene1() {
        //given
        Doctor doctor = registerDoctor("Jose","j@mail.com","123456", Specialty.CARDIOLOGY);
        Patient patient = registerPatient("antonio","a@mail.com","654321");
        registerAppointment(doctor,patient,getNextMonday10H());

        //when
        var freeDoctor = doctorRepository.selectDoctorWithSpecialtyOnDate(Specialty.CARDIOLOGY, getNextMonday10H());

        //then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("It must be return a doctor when consults database in that hour")
    void selectDoctorWithSpecialtyOnDateScene2() {

        //given
        Doctor doctor = registerDoctor("Jose","j@mail.com","123456",Specialty.CARDIOLOGY);

        //when
        var freeDoctor = doctorRepository.selectDoctorWithSpecialtyOnDate(Specialty.CARDIOLOGY, getNextMonday10H());

        //then
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String dni, Specialty specialty) {
        var doctor = new Doctor(dataDoctor(name, email, dni, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String dni) {
        var patient = new Patient(dataPatient(name, email, dni));
        em.persist(patient);
        return patient;
    }

    private DataDoctorRegister dataDoctor(String name, String email, String dni, Specialty specialty) {
        return new DataDoctorRegister(
                name,
                email,
                "61999999999",
                dni,
                specialty,
                dataAddress()
        );
    }

    private DataPatientRegister dataPatient(String name, String email, String dni) {
        return new DataPatientRegister(
                name,
                email,
                "61999999999",
                dni,
                dataAddress()
        );
    }

    private DataAddress dataAddress() {
        return new DataAddress(
                " calle",
                "azul",
                "acapulco",
                "321",
                "12"
        );
    }

    private static LocalDateTime getNextMonday10H() {
        return LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
    }

}