package com.gymster.backend.controllers;

import com.gymster.backend.DTO.AuthCheckDTO;
import com.gymster.backend.security.DTO.AuthCheckBody;
import com.gymster.backend.DTO.AuthenticationResponseDTO;
import com.gymster.backend.security.DTO.LoginRequestBody;
import com.gymster.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestBody loginRequestBody){
        try{
            return ResponseEntity.ok(authenticationService.authorize(loginRequestBody));
        }
        catch (BadCredentialsException | InternalAuthenticationServiceException badCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex){
            System.out.println(ex.getClass());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/check")
    public ResponseEntity<AuthCheckDTO> isAuthValid(@RequestBody AuthCheckBody authCheckBody){
        try{
            return ResponseEntity.ok(new AuthCheckDTO(authenticationService.isAuthValid(authCheckBody)));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
