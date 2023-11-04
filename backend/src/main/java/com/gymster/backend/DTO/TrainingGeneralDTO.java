package com.gymster.backend.DTO;

import com.gymster.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainingGeneralDTO {

    private Long trainingId;
    private String trainingTitle;

    private String trainingDescription;

    private int likes;

    private int dislikes;

    private String username;

    private String photoUrl;
}
