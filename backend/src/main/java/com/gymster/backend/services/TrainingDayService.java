package com.gymster.backend.services;

import com.gymster.backend.models.TrainingDay;
import com.gymster.backend.repositories.TrainingDayRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingDayService {

    private final TrainingDayRepository trainingDayRepository;

    public TrainingDayService(TrainingDayRepository trainingDayRepository){
        this.trainingDayRepository = trainingDayRepository;
    }

    public void save(TrainingDay trainingDay){
        trainingDayRepository.save(trainingDay);
    }


}
