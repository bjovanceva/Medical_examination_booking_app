package org.example.medical_examination_booking_app.service.application;

import dto.DisplayUserDto;

import java.util.Optional;

public interface UserApplicationService{

    Optional<DisplayUserDto> findByUsername(String username);
}
