package com.gymster.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TrainingDTO {

    private String trainingTitle;
    private String trainingDesc;
    private List<TrainingDetailDTO> trainingDetails;

}
