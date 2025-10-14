package org.example.project.service;

import org.example.project.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainerService {
    //Trainer createTrainer(Trainer trainer);

    List<Trainer> findAll();

    Trainer findTrainerByUsername(String username);

    void delete(String username);

    Trainer updateTrainerProfile(String username, Trainer updatedTrainer);

   /* void changePassword(String username, String newPassword);

    void activate(String username);

    void deactivate(String username);

    boolean authenticate(String username, String password);*/
}
