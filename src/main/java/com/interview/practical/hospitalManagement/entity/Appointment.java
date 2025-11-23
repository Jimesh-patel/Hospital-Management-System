package com.interview.practical.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Column(length = 500)
    private String reason;

    @ManyToOne   // Many Appointment to One Patient - Owning Side
    @JoinColumn(name = "patient_id", nullable = false) // Patient is required in appointment
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false) // default name doctor_id
    private Doctor doctor;
}





/*
Notes :
 1. The owning side dictates the FK updates.
 2. Updates to the mapped filed on the Inverse Side cannot update FK.
 3. Parent control lifecycle of others.
    Patient is deleted --> All appointments are deleted auto.
 */