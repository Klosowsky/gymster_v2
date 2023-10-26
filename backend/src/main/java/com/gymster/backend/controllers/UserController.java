package com.gymster.backend.controllers;

import com.gymster.backend.DTO.RegisterResponseDTO;
import com.gymster.backend.security.models.RegisterRequestBody;
import com.gymster.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestBody registerRequestBody){
        try {
            userService.register(registerRequestBody);
            return ResponseEntity.ok().build();
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegisterResponseDTO(ex.getMessage()));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterResponseDTO("Failed to register: "+ex.getMessage()));
        }
    }







}
