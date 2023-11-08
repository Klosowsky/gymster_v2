package com.gymster.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TrainingUploadDetailDTO {
    private int id;
    private List<TrainingUploadInputDTO> inputs;
}
