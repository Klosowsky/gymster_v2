package com.gymster.backend.DTO;

import lombok.Data;

@Data
public class TrainingUploadInputDTO {

    private Long id;
    private int series;
    private int reps;
    private Long exercise;
}
