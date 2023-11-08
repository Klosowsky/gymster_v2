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
@Table(name = "training_sessions")
public class TrainingSession {

    @EmbeddedId
    SessionExerciseKey id;

    @ManyToOne
    @MapsId("trainingDay")
    @JoinColumn(name = "training_day_id")
    private TrainingDay trainingDay;

    @ManyToOne
    @MapsId("exercise")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    int series;
    int reps;



}
