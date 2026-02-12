package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MedicalExaminationReservationNotFoundException extends RuntimeException {
    public MedicalExaminationReservationNotFoundException(Long id) {
        super(String.format("Medical Examination Reservation with id: %d is not found", id));
    }
}
