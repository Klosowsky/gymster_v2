package com.gymster.backend.controllers;


import com.gymster.backend.DTO.TrainingIdDTO;
import com.gymster.backend.DTO.TrainingDTO;
import com.gymster.backend.models.*;
import com.gymster.backend.repositories.TrainingDayRepository;
import com.gymster.backend.repositories.TrainingRepository;
import com.gymster.backend.services.*;
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
    private final TrainingDayRepository trainingDayRepository;
    private final TrainingRepository trainingRepository;


    @GetMapping("/getall")
    public ResponseEntity<List<Training>> getAllTrainings(){
        try{
            return ResponseEntity.ok(trainingService.getAllTrainings());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getallbyname")
    public ResponseEntity<List<Training>> getAllTrainingsByName(String trainingName){
        try{
            return ResponseEntity.ok(trainingService.getAllTrainingsByName(trainingName));
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<TrainingIdDTO> uploadTraining (@RequestBody TrainingDTO trainingDTO){
        try{
            if(!trainingService.validateTraining(trainingDTO)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(new TrainingIdDTO(trainingService.uploadTraining(trainingDTO).getId()));
        }catch (Exception ex){
            System.out.println( ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/print")
    public ResponseEntity<TrainingDTO> printTraining(Long id){
        return ResponseEntity.ok(trainingService.printTraining(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTraining(Long trainingId){
        try{
            trainingService.deleteTraining(trainingId);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
