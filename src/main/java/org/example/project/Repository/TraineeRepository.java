package org.example.project.Repository;

import org.example.project.Model.Trainee;

import java.util.Optional;

public interface TraineeRepository  extends GenericRepository<Trainee>{
    Optional<Trainee> findByUsername(String username);

}
