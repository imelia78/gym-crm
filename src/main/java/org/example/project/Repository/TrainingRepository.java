package org.example.project.Repository;

import org.example.project.Model.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends GenericRepository<Training> {
    List<Training> findByTraineeUsernameAndCriteria(
            String username,
            LocalDate fromDate,
            LocalDate toDate,
            String trainerName,
            String trainingTypeName
    );

    List<Training> findByTrainerUsernameAndCriteria(
            String username,
            LocalDate fromDate,
            LocalDate toDate,
            String traineeName
    );
}
