package com.gymster.backend.repositories;

import com.gymster.backend.models.Training;
import com.gymster.backend.models.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingDayRepository extends JpaRepository<TrainingDay,Long> {

    List<TrainingDay> findAllByTraining(Training training);

}
