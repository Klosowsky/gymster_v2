package com.gymster.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@Data
@Embeddable
public class UserTrainingKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "training_id")
    Long trainingId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrainingKey that = (UserTrainingKey) o;

        if (!Objects.equals(userId, that.userId))
            return false;
        return Objects.equals(trainingId, that.trainingId);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (trainingId != null ? trainingId.hashCode() : 0);
        return result;
    }


}
