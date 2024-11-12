package com.dev.homework.university.apierreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/teste")
public class TestController {

    @GetMapping("/hello-word")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Hello Word, man, eu te disse! kkkk");
    }

    @PostMapping("/post-test")
    public ResponseEntity<String> postTest() {
        return ResponseEntity.ok("Hello World");
    }
}
