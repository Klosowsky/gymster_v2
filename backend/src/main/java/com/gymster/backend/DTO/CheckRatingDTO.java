package com.gymster.backend.DTO;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CheckRatingDTO {
    private final int likes;
    private final int dislikes;
    private final int rating;
}
