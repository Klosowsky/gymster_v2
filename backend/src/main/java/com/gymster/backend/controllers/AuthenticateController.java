package com.gymster.backend.controllers;

import com.gymster.backend.security.models.AuthenticationResponse;
import com.gymster.backend.security.models.LoginRequestBody;
import com.gymster.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticationService authenticationService;


    @RequestMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestBody loginRequestBody){
        try{
            System.out.println("test1");
            AuthenticationResponse authResponse = authenticationService.authorize(loginRequestBody);
            System.out.println("test6");
            System.out.println("token = " +authResponse.getToken());
            return ResponseEntity.ok(authResponse);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
