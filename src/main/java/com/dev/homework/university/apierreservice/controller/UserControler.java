package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.payload.LoginPayload;
import com.dev.homework.university.apierreservice.payload.RegisterPayload;
import com.dev.homework.university.apierreservice.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControler {

    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

//    @Operation(tags = { "UserControler" })
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginPayload payload) {
        if (userService.login(payload)) {
            return ResponseEntity.ok("user login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user login failed!");
        }
    }

//    @Operation(tags = { "UserControler" })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterPayload payload) {
        try {
            userService.register(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body("user registration successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user registration failed: " + e.getMessage());
        }
    }
}