package com.interview.practical.hospitalManagement.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping
    public String test() {
        return "Server is running !";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello From Auth";
    }
}
