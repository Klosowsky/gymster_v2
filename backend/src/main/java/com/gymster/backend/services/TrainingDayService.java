package com.gymster.backend.services;

import com.gymster.backend.models.Training;
import com.gymster.backend.models.TrainingDay;
import com.gymster.backend.repositories.TrainingDayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingDayService {

    private final TrainingDayRepository trainingDayRepository;

    public TrainingDayService(TrainingDayRepository trainingDayRepository){
        this.trainingDayRepository = trainingDayRepository;
    }

    public void save(TrainingDay trainingDay){
        trainingDayRepository.save(trainingDay);
    }

    public List<TrainingDay> getDaysForTraining(Training training){
        return trainingDayRepository.findAllByTraining(training);
    }


}
