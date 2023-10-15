package com.gymster.backend.controllers;

import com.gymster.backend.models.Exercise;
import com.gymster.backend.repositories.ExerciseRepository;
import com.gymster.backend.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloController {

    ExerciseService exerciseService;

    @Autowired
    public HelloController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @RequestMapping("/hello")
    public ResponseEntity<String> hello(){


        String jsonData = "Hello world!!";
        Exercise exercise = new Exercise();
        exercise.setName("test");
        exerciseService.saveExercise(exercise);



        return new ResponseEntity<>(jsonData, HttpStatus.OK);

    }

}
