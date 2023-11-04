package com.gymster.backend.services;

import com.gymster.backend.DTO.TrainingGeneralDTO;
import com.gymster.backend.models.Training;
import com.gymster.backend.parser.TrainingParser;
import com.gymster.backend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> getAllTrainings(){
        return trainingRepository.findAll();
    }

    public void save(Training training){
        trainingRepository.save(training);
    }

    public List<Training> getAllTrainingsByName(String titleSearch){
        return extractFromList(trainingRepository.findAll(),titleSearch);
    }



    private List<Training> extractFromList(List<Training> trainingList, String titleSearch){
        List<Training> extractedList = new ArrayList<>();
        for ( Training training : trainingList){
            if(training.getTitle().toLowerCase().contains(titleSearch.toLowerCase())){
                extractedList.add(training);
            }
        }
        return extractedList;
    }


}
