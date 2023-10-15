package com.gymster.backend.services;

import com.gymster.backend.models.Exercise;
import com.gymster.backend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    ExerciseRepository exerciseRepository;
    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository=exerciseRepository;
    }

    public void saveExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }
}
