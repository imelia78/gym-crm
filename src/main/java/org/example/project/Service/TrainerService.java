package org.example.project.Service;

import org.example.project.Model.Trainee;
import org.example.project.Model.Trainer;

import java.util.List;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);

    List<Trainer> findAll();

    Trainer getTrainerByUsername(String username);

    void delete(Long id);

    Trainer updateTrainerProfile(String username, Trainer updatedTrainer);

    void changePassword(String username, String newPassword);

    void activate(String username);

    void deactivate(String username);

    boolean authenticate(String username, String password);
}
