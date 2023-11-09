package com.gymster.backend.controllers;


import com.gymster.backend.DTO.ItemNameDTO;
import com.gymster.backend.models.Exercise;
import com.gymster.backend.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/add")
    public ResponseEntity<Exercise> addExercise(@RequestBody ItemNameDTO itemNameDTO){
        try{
            return ResponseEntity.ok(exerciseService.saveExercise(new Exercise(itemNameDTO.getItemName())));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Exercise>> getAllExercises(){
        try{
            return ResponseEntity.ok(exerciseService.getAllExercises());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
