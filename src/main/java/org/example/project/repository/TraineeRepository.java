package org.example.project.repository;

import org.example.project.model.Trainee;

import java.util.Optional;

public interface TraineeRepository extends GenericRepository<Trainee> {
    Optional<Trainee> findByUsername(String username);

}
