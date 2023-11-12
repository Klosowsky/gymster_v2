package com.gymster.backend.services;

import com.gymster.backend.DTO.UserDetailsDTO;
import com.gymster.backend.models.User;
import com.gymster.backend.models.UserDetails;
import com.gymster.backend.repositories.UserDetailsRepository;
import com.gymster.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class MyUserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public MyUserDetailsService(UserDetailsRepository userDetailsRepository, ResourceLoader resourceLoader, UserRepository userRepository){
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    public void save(UserDetails userDetails){
        userDetailsRepository.save(userDetails);
    }

    @Transactional
    public void update(UserDetailsDTO userDetailsDTO){
        try {
            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
            UserDetails userDetails = userDetailsRepository.findById(user.getUserId()).orElseThrow();
            if (userDetailsDTO.getFile() != null) {
                userDetails.setProfilePhoto(userDetailsDTO.getFile().getBytes());
            }
            if (!userDetailsDTO.getEmail().equals("")){
                userDetails.setEmail(userDetailsDTO.getEmail());
            }
            userDetailsRepository.save(userDetails);
        }catch (Exception ex){
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }
    public static byte[] getDefaultImage() {
        try {
            Resource resource = new ClassPathResource("static/default_profile.jpg");
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public UserDetails getByUsername(String username){
        return userDetailsRepository.findById(userRepository.findByUsername(username).orElseThrow().getUserId()).orElseThrow();
    }

}
