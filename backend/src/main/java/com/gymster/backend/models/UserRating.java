package com.gymster.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_ratings")
public class UserRating {

    @EmbeddedId
    UserTrainingKey id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("training")
    @JoinColumn(name = "training_id")
    private Training training;

    int rating;

}
