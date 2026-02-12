package org.example.medical_examination_booking_app.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ActiveReservationListNotFound extends RuntimeException {
        public ActiveReservationListNotFound() {
            super("Active Reservation List Not Found");
        }

}
