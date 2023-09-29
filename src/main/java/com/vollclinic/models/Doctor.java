package com.vollclinic.models;

import com.vollclinic.dto.DataDoctorList;
import com.vollclinic.dto.DataDoctorRegister;
import com.vollclinic.dto.DataDoctorUpdate;
import com.vollclinic.dto.DataPatientList;
import com.vollclinic.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Doctor")
@Table(name = "doctors")
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String dni;
    private Boolean isActive;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(DataDoctorRegister dataDoctorRegister) {
        this.name = dataDoctorRegister.name();
        this.email = dataDoctorRegister.email();
        this.phone = dataDoctorRegister.phone();
        this.dni = dataDoctorRegister.dni();
        this.isActive = true;
        this.specialty = dataDoctorRegister.specialty();
        this.address = new Address(dataDoctorRegister.address());
    }

    public void updateData(DataDoctorUpdate dataDoctorUpdate) {
        if (dataDoctorUpdate.name() != null) {
            this.name = dataDoctorUpdate.name();
        }
        if (dataDoctorUpdate.dni() != null) {
            this.dni = dataDoctorUpdate.dni();
        }
        if (dataDoctorUpdate.address() != null) {
            this.address = address.updateData(dataDoctorUpdate.address());
        }
    }

    public void deactivateDoctor() {
        this.isActive = false;
    }

}
