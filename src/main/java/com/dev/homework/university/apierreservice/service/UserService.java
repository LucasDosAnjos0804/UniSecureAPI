package com.dev.homework.university.apierreservice.service;

import com.dev.homework.university.apierreservice.entity.Users;
import com.dev.homework.university.apierreservice.payload.LoginPayload;
import com.dev.homework.university.apierreservice.payload.RegisterPayload;
import com.dev.homework.university.apierreservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

    }

    public boolean login(LoginPayload payload) {
        return userRepository.findByUsername(payload.getUsername())
                .filter(u -> Objects.equals(payload.getPassword(), u.getPassword()))
                .isPresent();
    }

    public void register(RegisterPayload payload) {
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(
                Users.builder()
                        .username(payload.getUsername())
                        .password(payload.getPassword())
                        .build()
        );
    }
}