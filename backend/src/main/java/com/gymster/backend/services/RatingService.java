package com.gymster.backend.services;

import com.gymster.backend.DTO.RatingDTO;
import com.gymster.backend.models.Training;
import com.gymster.backend.models.User;
import com.gymster.backend.models.UserRating;
import com.gymster.backend.models.UserTrainingKey;
import com.gymster.backend.repositories.RatingRepository;
import com.gymster.backend.repositories.TrainingRepository;
import com.gymster.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, TrainingRepository trainingRepository){
        this.ratingRepository=ratingRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }


    @Transactional
    public void setRating(RatingDTO ratingDTO){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        Training training = trainingRepository.findById(ratingDTO.getTrainingId()).orElseThrow();
        UserTrainingKey userTrainingKey = new UserTrainingKey();
        userTrainingKey.setTrainingId(ratingDTO.getTrainingId());
        userTrainingKey.setUserId(user.getUserId());
        UserRating userRating = UserRating.builder()
                .id(userTrainingKey)
                .user(user)
                .training(training)
                .rating(ratingDTO.getRating())
                .build();
        System.out.println(userTrainingKey);
        System.out.println(userRating);



        switch (checkRating(ratingDTO.getTrainingId())) {
            case -1 -> {
                handleNegativeRating(training, userRating);
            }
            case 0 -> {
                handleNewRating(training, userRating);
            }
            case 1 -> {
                handlePositiveRating(training, userRating);
            }
        }

        trainingRepository.save(training);

    }

    private void handleNegativeRating(Training training, UserRating userRating) {
        if (userRating.getRating() == 1) {
            training.setLikes(training.getLikes() + 1);
            training.setDislikes(training.getDislikes() - 1);
            ratingRepository.save(userRating);
        }
        else if(userRating.getRating() == -1){
            training.setDislikes(training.getDislikes() - 1);
            ratingRepository.delete(userRating);
        }
    }

    private void handleNewRating(Training training, UserRating userRating) {
        if (userRating.getRating() == 1) {
            training.setLikes(training.getLikes() + 1);
        } else if (userRating.getRating() == -1) {
            training.setDislikes(training.getDislikes() + 1);
        }
        ratingRepository.save(userRating);
    }

    private void handlePositiveRating(Training training, UserRating userRating) {
        if (userRating.getRating() == -1) {
            training.setLikes(training.getLikes() - 1);
            training.setDislikes(training.getDislikes() + 1);
            ratingRepository.save(userRating);
        }
        else if(userRating.getRating() == 1){
            training.setLikes(training.getLikes() - 1);
            ratingRepository.delete(userRating);
        }
    }

    public int checkRating(Long trainingId){
        try {
            UserTrainingKey userTrainingKey = new UserTrainingKey();
            userTrainingKey.setTrainingId(trainingId);
            userTrainingKey.setUserId(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow().getUserId());
            return ratingRepository.findById(userTrainingKey).orElseThrow().getRating();
        }catch (Exception ex){
            return 0;
        }

    }



}
