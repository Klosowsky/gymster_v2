package com.gymster.backend.repositories;

import com.gymster.backend.models.Training;
import com.gymster.backend.models.UserRating;
import com.gymster.backend.models.UserTrainingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<UserRating, UserTrainingKey> {
    public List<UserRating> findAllByTraining(Training training);

}
