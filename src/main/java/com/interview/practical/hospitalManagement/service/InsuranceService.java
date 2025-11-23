package com.interview.practical.hospitalManagement.service;

import com.interview.practical.hospitalManagement.entity.Insurance;
import com.interview.practical.hospitalManagement.entity.Patient;
import com.interview.practical.hospitalManagement.repository.InsuranceRepository;
import com.interview.practical.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsurance(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + patientId));
        patient.setInsurance(null);
        return patient;

    }
}




/*
Notes :
1. @RequiredArgsConstructor takes all final + NonNull fields and create constructor
   So Dependency injection happens automatic without constructor or @autowire
 */
