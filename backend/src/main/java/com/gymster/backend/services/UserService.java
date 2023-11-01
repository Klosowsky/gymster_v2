package com.gymster.backend.services;

import com.gymster.backend.models.User;
import com.gymster.backend.models.UserDetails;
import com.gymster.backend.repositories.RoleRepository;
import com.gymster.backend.repositories.UserDetailsRepository;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.security.models.RegisterRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Transactional
    public void register(RegisterRequestBody registerRequestBody){
        if(registerRequestBody.getUsername()==null || registerRequestBody.getUsername().length()<6 || registerRequestBody.getUsername().length() > 15){
            throw new IllegalArgumentException("Wrong username!");
        }
        if(registerRequestBody.getPassword()==null || registerRequestBody.getPassword().length()<8 || registerRequestBody.getPassword().length() > 16){
            throw new IllegalArgumentException("Wrong password!");
        }
        if(registerRequestBody.getEmail()==null || !registerRequestBody.getEmail().contains("@")){
            throw new IllegalArgumentException("Wrong email!");
        }
        if(userRepository.findByUsername(registerRequestBody.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username is already used!");
        }
        if(!registerRequestBody.getPassword().equals(registerRequestBody.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords are not equal!");
        }

        User.UserBuilder userBuilder = User.builder();
        User user = userBuilder.username(registerRequestBody.getUsername()).
                password(passwordEncoder.encode(registerRequestBody.getPassword())).
                email(registerRequestBody.getEmail())
                .role(roleRepository.findByName("User")).build();
        UserDetails userDetails = new UserDetails(null,user,registerRequestBody.getEmail());
        userRepository.save(user);
        userDetailsRepository.save(userDetails);
    }



}
