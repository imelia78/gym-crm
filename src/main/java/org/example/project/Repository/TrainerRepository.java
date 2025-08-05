package org.example.project.Repository;

import org.example.project.Model.Trainer;

import java.util.Optional;

public interface TrainerRepository extends GenericRepository<Trainer> {
    Optional<Trainer> findByUsername(String username);

}
