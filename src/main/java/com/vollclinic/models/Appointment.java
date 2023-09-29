package com.vollclinic.models;

import com.vollclinic.enums.CancellationReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;
    @Column(name="cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime date){
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    public void cancelAppointment(CancellationReason cancellationReason){
        this.cancellationReason = cancellationReason;
    }

}
