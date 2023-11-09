package com.gymster.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TrainingDetailDTO {
    private int id;
    private List<TrainingInputDTO> inputs;
}
