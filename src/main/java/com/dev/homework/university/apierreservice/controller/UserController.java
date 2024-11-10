package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.payload.LoginPayload;
import com.dev.homework.university.apierreservice.payload.RegisterPayload;
import com.dev.homework.university.apierreservice.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginPayload payload) {
        log.info("User trying to login with username: {}", payload.getUsername());
        if (userService.login(payload)) {
            return ResponseEntity.ok("user login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user login failed!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterPayload payload) {
        log.info("User trying to register with username: {}", payload.getUsername());
        try {
            userService.register(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body("user registration successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user registration failed: " + e.getMessage());
        }
    }
}