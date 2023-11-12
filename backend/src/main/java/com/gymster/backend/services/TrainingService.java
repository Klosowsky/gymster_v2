package com.gymster.backend.services;

import com.gymster.backend.DTO.TrainingDTO;
import com.gymster.backend.DTO.TrainingDetailDTO;
import com.gymster.backend.DTO.TrainingInputDTO;
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
    private final RatingRepository ratingRepository;

    public TrainingService(TrainingRepository trainingRepository, UserRepository userRepository, TrainingDayRepository trainingDayRepository, TrainingSessionRepository trainingSessionRepository, ExerciseRepository exerciseRepository, RatingRepository ratingRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingDayRepository = trainingDayRepository;
        this.trainingSessionRepository = trainingSessionRepository;
        this.exerciseRepository = exerciseRepository;
        this.ratingRepository = ratingRepository;
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

    public Training getTrainingById(Long id){
        return trainingRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackOn = Exception. class)
    public Training uploadTraining(TrainingDTO trainingDTO){
        Training training = new Training();
        try {
            training.setTitle(trainingDTO.getTrainingTitle());
            training.setDescription(trainingDTO.getTrainingDesc());
            training.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow());
            trainingRepository.save(training);
            for(TrainingDetailDTO trainingDetailDTO : trainingDTO.getTrainingDetails()){
                TrainingDay trainingDay = new TrainingDay(null,training, trainingDetailDTO.getId());
                trainingDayRepository.save(trainingDay);
                for(TrainingInputDTO trainingInputDTO : trainingDetailDTO.getInputs()){
                    SessionExerciseKey sessionExerciseKey = new SessionExerciseKey();
                    sessionExerciseKey.setTrainingDayId(trainingDay.getId());
                    sessionExerciseKey.setExerciseId(trainingInputDTO.getExercise());
                    TrainingSession trainingSession = new TrainingSession(
                            sessionExerciseKey,
                            trainingDay,
                            exerciseRepository.findById(trainingInputDTO.getExercise()).orElseThrow(),
                            trainingInputDTO.getSeries(),
                            trainingInputDTO.getReps()
                            );
                    trainingSessionRepository.save(trainingSession);
                }
            }
        }catch (Exception ex){
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

    public boolean validateTraining(TrainingDTO trainingDTO){
        try {
            if (trainingDTO.getTrainingTitle() == null || trainingDTO.getTrainingDesc() == null) {
                return false;
            }
            for (TrainingDetailDTO trainingDetailDTO : trainingDTO.getTrainingDetails()) {
                for (TrainingInputDTO trainingInputDTO : trainingDetailDTO.getInputs()) {
                    if (trainingInputDTO.getExercise() == null ||
                            exerciseRepository.findById(trainingInputDTO.getExercise()).orElse(null) == null ||
                            trainingInputDTO.getReps() == 0 ||
                            trainingInputDTO.getSeries() == 0
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

    public TrainingDTO printTraining(Long id){
        try {
            Training training = trainingRepository.findById(id).orElseThrow();
            List<TrainingDetailDTO> trainingDetailDTOList = new ArrayList<>();
            for(TrainingDay trainingDay : trainingDayRepository.findAllByTraining(training)){
                TrainingDetailDTO trainingDetailDTO = new TrainingDetailDTO();
                trainingDetailDTO.setId(trainingDay.getDayNumber());

                List<TrainingInputDTO> trainingInputDTOList = new ArrayList<>();
                int i = 0;
                for(TrainingSession trainingSession : trainingSessionRepository.findAllByTrainingDay(trainingDay)){
                    TrainingInputDTO trainingInputDTO = TrainingInputDTO.builder()
                            .id((long) i)
                            .exerciseName(trainingSession.getExercise().getName())
                            .series(trainingSession.getSeries())
                            .reps(trainingSession.getReps())
                            .build();
                    trainingInputDTOList.add(trainingInputDTO);
                    i++;
                }
                trainingDetailDTO.setInputs(trainingInputDTOList);
                trainingDetailDTOList.add(trainingDetailDTO);
            }
            return TrainingDTO.builder()
                    .id(training.getId())
                    .trainingTitle(training.getTitle())
                    .trainingDesc(training.getDescription())
                    .username(training.getUser().getUsername())
                    .profilePhoto(training.getUser().getUserDetails().getProfilePhoto())
                    .likes(training.getLikes())
                    .dislikes(training.getDislikes())
                    .trainingDetails(trainingDetailDTOList)
                    .build();

        }catch (Exception ex){
            return null;
        }
    }

    @Transactional
    public void deleteTraining(Long trainingId){

        Training training = trainingRepository.findById(trainingId).orElseThrow();
        if(!training.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new UnsupportedOperationException();
        }
        List<TrainingDay> trainingDayList = trainingDayRepository.findAllByTraining(training);
        for(TrainingDay trainingDay : trainingDayList){
            List<TrainingSession> trainingSessionList = trainingSessionRepository.findAllByTrainingDay(trainingDay);
            trainingSessionRepository.deleteAll(trainingSessionList);
        }
        trainingDayRepository.deleteAll(trainingDayList);
        ratingRepository.deleteAll(ratingRepository.findAllByTraining(training));
        trainingRepository.delete(training);
    }
}
