package com.gymster.backend.services;

import com.gymster.backend.models.Exercise;
import com.gymster.backend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    @Autowired
    ExerciseRepository exerciseRepository;

    public void saveExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }
}
