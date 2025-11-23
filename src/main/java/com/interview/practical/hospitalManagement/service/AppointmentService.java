package com.interview.practical.hospitalManagement.service;

import com.interview.practical.hospitalManagement.entity.Appointment;
import com.interview.practical.hospitalManagement.entity.Doctor;
import com.interview.practical.hospitalManagement.entity.Patient;
import com.interview.practical.hospitalManagement.repository.AppointmentRepository;
import com.interview.practical.hospitalManagement.repository.DoctorRepository;
import com.interview.practical.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment already exists");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // just to maintain Bi-directional consistency

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointment(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // auto call update, because it is dirty
        doctor.getAppointments().add(appointment); // Just for Bidirectional consistency
        return appointment;

    }
}
