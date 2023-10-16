package com.gymster.backend.services;


import com.gymster.backend.models.User;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.security.models.AuthenticationResponse;
import com.gymster.backend.security.models.LoginRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;


    public AuthenticationResponse authorize(LoginRequestBody loginRequestBody){
        System.out.println("test2 "+loginRequestBody.getUsername());
        System.out.println("test3");
        User user = null;
        try {
            user = userRepository.findByUsername(loginRequestBody.getUsername()).orElseThrow();
        }catch (Exception ex){
            System.out.println("ex "+ex.getMessage());
        }
        System.out.println("test4 "+user.getUsername());
        String token = jwtTokenService.generateToken(user);
        System.out.println("test5");
        return new AuthenticationResponse(token);
    }






}
