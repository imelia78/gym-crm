package org.example.project.repository;

import org.example.project.model.Trainer;

import java.util.Optional;

public interface TrainerRepository extends GenericRepository<Trainer> {
    Optional<Trainer> findByUsername(String username);

}
