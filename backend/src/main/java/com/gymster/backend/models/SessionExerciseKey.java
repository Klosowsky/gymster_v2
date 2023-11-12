package com.gymster.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class SessionExerciseKey implements Serializable {
    @Column(name = "training_day_id")
    Long trainingDayId;

    @Column(name = "exercise_id")
    Long exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionExerciseKey that = (SessionExerciseKey) o;

        if (!Objects.equals(trainingDayId, that.trainingDayId))
            return false;
        return Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        int result = trainingDayId != null ? trainingDayId.hashCode() : 0;
        result = 31 * result + (exerciseId != null ? exerciseId.hashCode() : 0);
        return result;
    }


}
