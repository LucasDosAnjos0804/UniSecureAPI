package com.dev.homework.university.apierreservice.service;

import com.dev.homework.university.apierreservice.entity.User;

import com.dev.homework.university.apierreservice.payload.LoginPayload;
import com.dev.homework.university.apierreservice.payload.RegisterPayload;
import com.dev.homework.university.apierreservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(LoginPayload payload) {
        return userRepository.findByUsername(payload.getUsername())
                .filter(user -> passwordEncoder.matches(payload.getPassword(), user.getPassword()))
                .isPresent();
    }

    public void register(RegisterPayload payload) {
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(
                User.builder()
                        .username(payload.getUsername())
                        .password(passwordEncoder.encode(payload.getPassword()))
                        .build()
        );
    }
}