package com.gymster.backend.controllers;


import com.gymster.backend.DTO.TrainingGeneralDTO;
import com.gymster.backend.DTO.TrainingNameDTO;
import com.gymster.backend.models.Training;
import com.gymster.backend.models.User;
import com.gymster.backend.services.TrainingService;
import com.gymster.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping("/getall")
    public ResponseEntity<List<Training>> getAllTrainings(){
        try{
            return ResponseEntity.ok(trainingService.getAllTrainings());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/getallbyname")
    public ResponseEntity<List<Training>> getAllTrainingsByName(@RequestBody TrainingNameDTO trainingNameDTO){
        try{
            return ResponseEntity.ok(trainingService.getAllTrainingsByName(trainingNameDTO.getTrainingName()));
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }




}
