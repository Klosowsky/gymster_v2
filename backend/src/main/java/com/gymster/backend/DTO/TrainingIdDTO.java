package com.gymster.backend.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class TrainingIdDTO {
    private Long trainingId;

    public TrainingIdDTO(Long id){
        this.trainingId=id;
    }
}
