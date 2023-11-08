package com.gymster.backend.repositories;

import com.gymster.backend.models.SessionExerciseKey;
import com.gymster.backend.models.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, SessionExerciseKey> {

}
