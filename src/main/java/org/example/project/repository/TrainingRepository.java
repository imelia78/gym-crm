package org.example.project.repository;

import org.example.project.model.Training;
import org.example.project.model.TrainingType;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends GenericRepository<Training> {
    List<Training> findByTraineeId(Long traineeId);

    List<Training> findByTrainerId(Long trainerId);

    List<Training> findByDate(LocalDate date);

    List<Training> findByType(TrainingType type);
}
