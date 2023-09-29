package com.vollclinic.models;

import com.vollclinic.dto.DataPatientList;
import com.vollclinic.dto.DataPatientRegister;
import com.vollclinic.dto.DataPatientUpdate;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String dni;
    private Boolean isActive;
    @Embedded
    private Address address;

    public Patient(DataPatientRegister dataPatientRegister) {
        this.name = dataPatientRegister.name();
        this.email = dataPatientRegister.email();
        this.phone = dataPatientRegister.phone();
        this.dni = dataPatientRegister.dni();
        this.isActive = true;
        this.address = new Address(dataPatientRegister.address());
    }

    public void updateData(DataPatientUpdate dataPatientUpdate) {
        if (dataPatientUpdate.name() != null) {
            this.name = dataPatientUpdate.name();
        }
        if (dataPatientUpdate.dni() != null) {
            this.dni = dataPatientUpdate.dni();
        }
        if (dataPatientUpdate.address() != null) {
            this.address = address.updateData(dataPatientUpdate.address());
        }
    }

    public void deactivatePatient() {
        this.isActive = false;
    }
}
