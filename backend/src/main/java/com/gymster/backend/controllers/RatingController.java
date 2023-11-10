package com.gymster.backend.controllers;

import com.gymster.backend.DTO.RatingDTO;
import com.gymster.backend.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/set")
    public ResponseEntity<String> setRating(@RequestBody RatingDTO ratingDTO){
        try {
            ratingService.setRating(ratingDTO);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/check")
    public ResponseEntity<Integer> checkRating(Long trainingId){
        try{
           return ResponseEntity.ok(ratingService.checkRating(trainingId));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
