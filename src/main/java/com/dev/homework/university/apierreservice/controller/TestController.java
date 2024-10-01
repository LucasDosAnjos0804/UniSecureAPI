package com.dev.homework.university.apierreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("test")
public class TestController {

    @GetMapping("/hello-word")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Hello Word");
    }
}
