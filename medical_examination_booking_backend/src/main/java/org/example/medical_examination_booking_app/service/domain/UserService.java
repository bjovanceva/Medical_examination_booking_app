package org.example.medical_examination_booking_app.service.domain;

import org.example.medical_examination_booking_app.model.domain.User;

public interface UserService {

    User findByUsername(String username);
}
