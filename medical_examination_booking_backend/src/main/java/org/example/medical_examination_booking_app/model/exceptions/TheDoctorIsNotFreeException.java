package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TheDoctorIsNotFreeException extends RuntimeException {
    public TheDoctorIsNotFreeException() {
        super("The doctor is not free");
    }
}
