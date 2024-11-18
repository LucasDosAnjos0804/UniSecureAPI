package com.dev.homework.university.apierreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/teste")
public class TestController {

    @GetMapping("/hello-word")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Hello Word, man, vamo ver se demora ksadf! kkkk");
    }

    @PostMapping("/post-test")
    public ResponseEntity<String> postTest() {
        return ResponseEntity.ok("Hello World");
    }
}
