package com.gymster.backend.repositories;

import com.gymster.backend.models.UserRating;
import com.gymster.backend.models.UserTrainingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<UserRating, UserTrainingKey> {
}
