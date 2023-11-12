package com.gymster.backend.services;


import com.gymster.backend.models.User;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.security.DTO.AuthCheckBody;
import com.gymster.backend.DTO.AuthenticationResponseDTO;
import com.gymster.backend.security.DTO.LoginRequestBody;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(), loginRequestBody.getPassword()));
        User user;
        try {
            user = userRepository.findByUsername(loginRequestBody.getUsername()).orElseThrow();
        }catch (Exception ex){
            throw new NullPointerException();
        }
        String token = jwtTokenService.generateToken(user);
        return new AuthenticationResponseDTO(token, user.getRole().getId());
    }

    @Transactional
    public boolean isAuthValid(AuthCheckBody authCheckBody){
        try {
            String token = authCheckBody.getToken();
            User user = userRepository.findByUsername(jwtTokenService.extractUsername(token)).orElseThrow();
            return user.getUsername().equals(authCheckBody.getUsername()) && user.getRole().getId().equals(authCheckBody.getRole()) && !jwtTokenService.isTokenExpired(token);
        }catch (Exception ex){
            return false;
        }
    }

}
