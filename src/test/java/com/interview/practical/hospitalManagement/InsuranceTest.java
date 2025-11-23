package com.interview.practical.hospitalManagement;

import com.interview.practical.hospitalManagement.entity.Appointment;
import com.interview.practical.hospitalManagement.entity.Insurance;
import com.interview.practical.hospitalManagement.entity.Patient;
import com.interview.practical.hospitalManagement.service.AppointmentService;
import com.interview.practical.hospitalManagement.service.InsuranceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
     public void testInsurance(){
         Insurance insurance = Insurance.builder()
                 .policyNumber("HDFC_1234")
                 .provider("HDFC")
                 .validUntil(LocalDate.of(2020, 1, 1))
                 .build();
        Patient patient = insuranceService.assignInsurance(insurance, 1L);
        System.out.println(patient);
     }

     @Test
     public void testAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cencer")
                .build();

        var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
        System.out.println(newAppointment);
     }

}
