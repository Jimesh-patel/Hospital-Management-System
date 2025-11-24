package com.interview.practical.hospitalManagement.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {


    @GetMapping("/hello")
    public String Hello() {
        return "Hello From Appointment";
    }
}
