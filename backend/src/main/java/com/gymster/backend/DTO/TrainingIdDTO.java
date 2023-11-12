package com.gymster.backend.DTO;

import lombok.Data;
@Data
public class TrainingIdDTO {
    private Long trainingId;

    public TrainingIdDTO(Long id){
        this.trainingId=id;
    }
}
