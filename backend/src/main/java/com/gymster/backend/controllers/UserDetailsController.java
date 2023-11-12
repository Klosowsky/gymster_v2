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

@RestController
@RequestMapping("/userdetails")
@RequiredArgsConstructor

public class UserDetailsController {

    private final MyUserDetailsService myUserDetailsService;

    @PostMapping("/update")
    public ResponseEntity<String> updateDetails(@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam("email") String email) {
        try {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            userDetailsDTO.setFile(file);
            userDetailsDTO.setEmail(email);
            myUserDetailsService.update(userDetailsDTO);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error!");
        }
    }

    @GetMapping("/getimage")
    public ResponseEntity<byte[]> getUserImage(String username) {
       try{
           return ResponseEntity.ok().body(myUserDetailsService.getByUsername(username).getProfilePhoto());
       } catch (Exception ex ){
           return ResponseEntity.status(500).build();
       }
    }
}