package org.example.medical_examination_booking_app.service.application.impl;

import dto.DisplayUserDto;
import org.example.medical_examination_booking_app.service.application.UserApplicationService;
import org.example.medical_examination_booking_app.service.domain.UserService;

import java.util.Optional;

public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
