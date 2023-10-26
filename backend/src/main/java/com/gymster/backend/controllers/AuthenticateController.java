package com.gymster.backend.controllers;

import com.gymster.backend.DTO.AuthCheckDTO;
import com.gymster.backend.security.models.AuthCheckBody;
import com.gymster.backend.security.models.AuthenticationResponse;
import com.gymster.backend.security.models.LoginRequestBody;
import com.gymster.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestBody loginRequestBody){
        try{
            System.out.println("test1");
            AuthenticationResponse authResponse = authenticationService.authorize(loginRequestBody);
            System.out.println("test6");
            System.out.println("token = " +authResponse.getToken());
            System.out.println("role = "+authResponse.getRoleId());
            return ResponseEntity.ok(authResponse);
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
        System.out.println("in contreoller");
        try{
                return ResponseEntity.ok(new AuthCheckDTO(authenticationService.isAuthValid(authCheckBody)));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
