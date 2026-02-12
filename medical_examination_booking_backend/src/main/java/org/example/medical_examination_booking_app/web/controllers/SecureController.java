package org.example.medical_examination_booking_app.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @GetMapping
    public String secureEndpoint() {
        return "This is a secure endpoint!";
    }
}
