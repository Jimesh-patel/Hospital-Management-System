package com.interview.practical.hospitalManagement;

import com.interview.practical.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.interview.practical.hospitalManagement.dto.PatientResponseDto;
import com.interview.practical.hospitalManagement.entity.Patient;
import com.interview.practical.hospitalManagement.repository.PatientRepository;
import com.interview.practical.hospitalManagement.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testTransactionMethods() {
//        1
        PatientResponseDto patient = patientService.getPatientById(1L);
        System.out.println(patient);
        System.out.println("---------------------------------------------------------------------------------------------------------------");
//        2
        Patient patient2 = patientRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Patient not " + "found with id: 1"));
        System.out.println(patient2);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        3
        Patient patient3 = patientRepository.findByName("Diya Patel");
        System.out.println(patient3);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        4
        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1988, 3, 15), "diya" + ".patel@example.com");
        for(Patient p: patientList) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        5
        List<Patient> patientList2 = patientRepository.findByBornAfterDate(LocalDate.of(1993, 3, 14));
        for(Patient p: patientList2) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        6
        Page<Patient> patientList3 = patientRepository.findAllPatients(PageRequest.of(1, 2, Sort.by("name")));
        for(Patient p: patientList3) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        7
        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(BloodGroupCountResponseEntity objects : bloodGroupList) {
            System.out.println(objects.getBloodGroupType() +" "+ objects.getCount());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        8
        int rowsUpdated = patientRepository.updateNameWithId("Arav Sharma", 1L);
        System.out.println(rowsUpdated);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

//        9
        List<BloodGroupCountResponseEntity> bloodGroupList2 = patientRepository.countEachBloodGroupType();
        for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupList2) {
            System.out.println(bloodGroupCountResponse);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

    }

    @Test
    public void testTransactionMethods2() {
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        // N + 1 problem when appointments in patient is not exclude and set FetchType EAGER
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        // Solution of N+1 problem with custom query
        List<Patient> patientList2 = patientRepository.findAllPatientWithAppointments();
        System.out.println(patientList2);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

    }
}