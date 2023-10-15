package com.gymster.backend.services;

import com.gymster.backend.models.User;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.security.models.RegisterRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void register(RegisterRequestBody registerRequestBody){
        if(registerRequestBody.getUsername()==null || registerRequestBody.getUsername().length()<6 || registerRequestBody.getUsername().length() > 18){
            throw new IllegalArgumentException("Wrong username");
        }
        if(registerRequestBody.getPassword()==null || registerRequestBody.getPassword().length()<8 || registerRequestBody.getPassword().length() > 18){
            throw new IllegalArgumentException("Wrong password");
        }
        if(registerRequestBody.getEmail()==null || !registerRequestBody.getEmail().contains("@")){
            throw new IllegalArgumentException("Wrong email");
        }
        User.UserBuilder userBuilder = User.builder();
        User user = userBuilder.username(registerRequestBody.getUsername()).
                password(registerRequestBody.getPassword()).
                email(registerRequestBody.getEmail()).build();
        userRepository.save(user);

    }



}
