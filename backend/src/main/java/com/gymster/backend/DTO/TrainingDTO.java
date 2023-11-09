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
    private String photoUrl;
    private int likes;
    private int dislikes;
    Long id;


}
