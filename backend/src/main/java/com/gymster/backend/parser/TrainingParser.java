package com.gymster.backend.parser;

import com.gymster.backend.DTO.TrainingGeneralDTO;
import com.gymster.backend.models.Training;

import java.util.ArrayList;
import java.util.List;

public class TrainingParser {

    public static TrainingGeneralDTO parseToGeneralDTO(Training training){
        return new TrainingGeneralDTO(training.getId(), training.getTitle(), training.getDescription(), training.getLikes(), training.getDislikes(), training.getUser().getUsername(),training.getUser().getUserDetails().getProfilePhoto());
    }

    public static List<TrainingGeneralDTO> parseToGeneralDTOList(List<Training> trainingList){
        List<TrainingGeneralDTO> trainingGeneralDTOList = new ArrayList<>();
        for (Training training : trainingList) {
            trainingGeneralDTOList.add(parseToGeneralDTO(training));
        }
        return trainingGeneralDTOList;
    }
}
