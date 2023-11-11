package com.gymster.backend.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrainingDTO {

    private String trainingTitle;
    private String trainingDesc;
    private List<TrainingDetailDTO> trainingDetails;
    private String username;
    private byte[] profilePhoto;
    private int likes;
    private int dislikes;
    Long id;


}
