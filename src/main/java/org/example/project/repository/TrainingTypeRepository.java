package org.example.project.repository;

import org.example.project.model.TrainingType;

import java.util.Optional;

public interface TrainingTypeRepository extends GenericRepository<TrainingType> {
    Optional<TrainingType> findByTrainingTypeName(String name);
}
