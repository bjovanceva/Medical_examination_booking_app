package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientWithNameNotFoundException extends RuntimeException {

    public PatientWithNameNotFoundException(String name) {
        super(String.format("Patient with name: %s was not found", name));
    }
}
