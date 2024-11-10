package com.dev.homework.university.apierreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/teste")
public class TestController {

    @GetMapping("/hello-word")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Hello Word");
    }

    @PostMapping("/post-test")
    public ResponseEntity<String> postTest() {
        return ResponseEntity.ok("Hello World");
    }
}
