package com.gymster.backend.services;

import com.gymster.backend.models.SessionExerciseKey;
import com.gymster.backend.models.TrainingSession;
import com.gymster.backend.repositories.TrainingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public TrainingSession saveTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    public List<TrainingSession> getAllTrainingSessions() {
        return trainingSessionRepository.findAll();
    }

    public Optional<TrainingSession> getTrainingSessionById(SessionExerciseKey id) {
        return trainingSessionRepository.findById(id);
    }

    public void deleteTrainingSession(SessionExerciseKey id) {
        trainingSessionRepository.deleteById(id);
    }

}
