package com.gymster.backend.repositories;

import com.gymster.backend.models.SessionExerciseKey;
import com.gymster.backend.models.TrainingDay;
import com.gymster.backend.models.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, SessionExerciseKey> {
    List<TrainingSession> findAllByTrainingDay(TrainingDay trainingDay);

}
