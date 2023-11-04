package com.gymster.backend.repositories;

import com.gymster.backend.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training,Long> {
}
