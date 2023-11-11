package com.gymster.backend.services;


import com.gymster.backend.models.User;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.security.models.AuthCheckBody;
import com.gymster.backend.DTO.AuthenticationResponseDTO;
import com.gymster.backend.security.models.LoginRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponseDTO authorize(LoginRequestBody loginRequestBody){
        System.out.println("test2 "+loginRequestBody.getUsername());
        System.out.println("test3");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(), loginRequestBody.getPassword()));
        User user = null;
        try {
            user = userRepository.findByUsername(loginRequestBody.getUsername()).orElseThrow();
        }catch (Exception ex){
            System.out.println("ex "+ex.getMessage());
        }
        System.out.println("test4 "+user.getUsername());
        String token = jwtTokenService.generateToken(user);
        return new AuthenticationResponseDTO(token, user.getRole().getId(),user.getUserDetails().getPhoto());
    }

    @Transactional
    public boolean isAuthValid(AuthCheckBody authCheckBody){
        try {
            String token = authCheckBody.getToken();
            User user = userRepository.findByUsername(jwtTokenService.extractUsername(token)).orElseThrow();
            return user.getUsername().equals(authCheckBody.getUsername()) && user.getRole().getId().equals(authCheckBody.getRole()) && !jwtTokenService.isTokenExpired(token);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
