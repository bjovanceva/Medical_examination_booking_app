package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(Long id) {
        super(String.format("Doctor with id: %d is not found", id));
    }
}
