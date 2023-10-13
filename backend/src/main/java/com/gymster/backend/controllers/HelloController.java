package com.gymster.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){

        String jsonData = "Hello world!!";
        return new ResponseEntity<>(jsonData, HttpStatus.OK);

    }

}
