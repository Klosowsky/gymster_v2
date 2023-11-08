package com.gymster.backend.services;

import com.gymster.backend.DTO.TrainingUploadDTO;
import com.gymster.backend.DTO.TrainingUploadDetailDTO;
import com.gymster.backend.DTO.TrainingUploadInputDTO;
import com.gymster.backend.models.SessionExerciseKey;
import com.gymster.backend.models.Training;
import com.gymster.backend.models.TrainingDay;
import com.gymster.backend.models.TrainingSession;
import com.gymster.backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingDayRepository trainingDayRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final ExerciseRepository exerciseRepository;

    public TrainingService(TrainingRepository trainingRepository, UserRepository userRepository, TrainingDayRepository trainingDayRepository, TrainingSessionRepository trainingSessionRepository, ExerciseRepository exerciseRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingDayRepository = trainingDayRepository;
        this.trainingSessionRepository = trainingSessionRepository;

        this.exerciseRepository = exerciseRepository;
    }

    public List<Training> getAllTrainings(){
        return trainingRepository.findAll();
    }

    public void save(Training training){
        trainingRepository.save(training);
    }

    public List<Training> getAllTrainingsByName(String titleSearch){
        return extractFromList(trainingRepository.findAll(),titleSearch);
    }

    @Transactional(rollbackOn = Exception. class)
    public Training uploadTraining(TrainingUploadDTO trainingUploadDTO){
        Training training = new Training();
        try {
            training.setTitle(trainingUploadDTO.getTrainingTitle());
            training.setDescription(trainingUploadDTO.getTrainingDesc());
            training.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow());
            trainingRepository.save(training);
            System.out.println("saved training - " +training.getId());
            for(TrainingUploadDetailDTO trainingUploadDetailDTO : trainingUploadDTO.getTrainingDetails()){
                TrainingDay trainingDay = new TrainingDay(null,training,trainingUploadDetailDTO.getId());
                trainingDayRepository.save(trainingDay);
                System.out.println("saved training day - " + trainingDay.getId());
                for(TrainingUploadInputDTO trainingUploadInputDTO : trainingUploadDetailDTO.getInputs()){
                    System.out.println("Input id = "+trainingUploadInputDTO.getId());
                    SessionExerciseKey sessionExerciseKey = new SessionExerciseKey();
                    sessionExerciseKey.setTrainingDayId(trainingDay.getId());
                    sessionExerciseKey.setExerciseId(trainingUploadInputDTO.getExercise());
                    TrainingSession trainingSession = new TrainingSession(
                            sessionExerciseKey,
                            trainingDay,
                            exerciseRepository.findById(trainingUploadInputDTO.getExercise()).orElseThrow(),
                            trainingUploadInputDTO.getSeries(),
                            trainingUploadInputDTO.getReps()
                            );
                    trainingSessionRepository.save(trainingSession);
                    System.out.println("saved session");
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return training;
    }



    private List<Training> extractFromList(List<Training> trainingList, String titleSearch){
        List<Training> extractedList = new ArrayList<>();
        for ( Training training : trainingList){
            if(training.getTitle().toLowerCase().contains(titleSearch.toLowerCase())){
                extractedList.add(training);
            }
        }
        return extractedList;
    }

    public boolean validateTraining(TrainingUploadDTO trainingUploadDTO){
        try {
            if (trainingUploadDTO.getTrainingTitle() == null || trainingUploadDTO.getTrainingDesc() == null) {
                return false;
            }
            for (TrainingUploadDetailDTO trainingUploadDetailDTO : trainingUploadDTO.getTrainingDetails()) {
                for (TrainingUploadInputDTO trainingUploadInputDTO : trainingUploadDetailDTO.getInputs()) {
                    if (trainingUploadInputDTO.getExercise() == null ||
                            exerciseRepository.findById(trainingUploadInputDTO.getExercise()).orElse(null) == null ||
                            trainingUploadInputDTO.getReps() == 0 ||
                            trainingUploadInputDTO.getSeries() == 0
                    ) {
                        return false;
                    }
                }
            }
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
