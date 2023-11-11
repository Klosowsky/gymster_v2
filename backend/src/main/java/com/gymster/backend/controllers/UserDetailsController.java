package com.gymster.backend.controllers;

import com.gymster.backend.DTO.UserDetailsDTO;
import com.gymster.backend.models.UserDetails;
import com.gymster.backend.repositories.UserDetailsRepository;
import com.gymster.backend.repositories.UserRepository;
import com.gymster.backend.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/userdetails")
@RequiredArgsConstructor

public class UserDetailsController {

    private final MyUserDetailsService myUserDetailsService;
    private final UserDetailsRepository userDetailsRepository;

    @PostMapping("/update")
    public ResponseEntity<String> handleFileUpload(@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam("email") String email) {
        try {
            System.out.println("Start1");
            System.out.println(file);
            System.out.println(email);
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            userDetailsDTO.setFile(file);
            userDetailsDTO.setEmail(email);
            myUserDetailsService.update(userDetailsDTO);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<byte[]> getImageById(Long id) {
       try{
        UserDetails userDetails = userDetailsRepository.findById(id).orElseThrow();
       return ResponseEntity.ok().body(userDetails.getProfilePhoto());
        } catch (Exception ex ){
            return ResponseEntity.status(500).build();
        }
    }



}
