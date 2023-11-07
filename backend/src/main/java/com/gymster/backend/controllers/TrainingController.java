package com.gymster.backend.controllers;


import com.gymster.backend.DTO.TrainingGeneralDTO;
import com.gymster.backend.DTO.TrainingNameDTO;
import com.gymster.backend.DTO.TrainingUploadDTO;
import com.gymster.backend.models.*;
import com.gymster.backend.repositories.ExerciseRepository;
import com.gymster.backend.repositories.TrainingDayRepository;
import com.gymster.backend.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingDayService trainingDayService;
    private final ExerciseService exerciseService;
    private final TrainingSessionService trainingSessionService;

    private final TrainingDayRepository trainingDayRepository;
    private final ExerciseRepository exerciseRepository;

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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTraining (@RequestBody TrainingUploadDTO trainingUploadDTO){
        try{
            trainingService.uploadTraining(trainingUploadDTO);
            return ResponseEntity.ok("ok");

        }catch (Exception ex){
            System.out.println( ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }



    }




}
