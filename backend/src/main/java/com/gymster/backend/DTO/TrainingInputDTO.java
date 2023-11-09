package com.gymster.backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingInputDTO {

    private Long id;
    private int series;
    private int reps;
    private Long exercise;
    private String exerciseName;
}
