package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReservationListNotFoundException extends RuntimeException {
    public ReservationListNotFoundException(Long id) {
        super(String.format("Reservation list with id: %d is not found", id));
    }
}
