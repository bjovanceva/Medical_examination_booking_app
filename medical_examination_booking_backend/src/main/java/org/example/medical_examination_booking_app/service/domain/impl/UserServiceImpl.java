package org.example.medical_examination_booking_app.service.domain.impl;

import org.example.medical_examination_booking_app.model.domain.User;
import org.example.medical_examination_booking_app.model.exceptions.UserNotFoundException;
import org.example.medical_examination_booking_app.repository.UserRepository;
import org.example.medical_examination_booking_app.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }
}
