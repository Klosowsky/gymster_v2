package com.gymster.backend.controllers;

import com.gymster.backend.DTO.CheckRatingDTO;
import com.gymster.backend.DTO.RatingDTO;
import com.gymster.backend.models.Training;
import com.gymster.backend.services.RatingService;
import com.gymster.backend.services.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController  {

    private final RatingService ratingService;
    private final TrainingService trainingService;

    @PostMapping("/set")
    public ResponseEntity<String> setRating(@RequestBody RatingDTO ratingDTO){
        try {
            ratingService.setRating(ratingDTO);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/check")
    public ResponseEntity<CheckRatingDTO> checkRating(Long trainingId){
        try{
            Training training = trainingService.getTrainingById(trainingId);
            return ResponseEntity.ok(new CheckRatingDTO(training.getLikes(),training.getDislikes(),ratingService.checkRating(trainingId)));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
