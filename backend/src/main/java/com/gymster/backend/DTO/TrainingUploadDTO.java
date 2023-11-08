package com.gymster.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TrainingUploadDTO {

    private String trainingTitle;
    private String trainingDesc;
    private List<TrainingUploadDetailDTO> trainingDetails;

}
