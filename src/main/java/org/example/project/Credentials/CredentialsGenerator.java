package org.example.project.Credentials;


import org.example.project.Service.TraineeService;
import org.example.project.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CredentialsGenerator {

    private final TraineeService traineeService;
    private final TrainerService trainerService;

    public CredentialsGenerator(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    public String generateUsername(String firstName, String lastName) {
        String baseUsername = firstName.trim().toLowerCase() + "." + lastName.trim().toLowerCase();
        String username = baseUsername;
        int postfix = 1;

        while (usernameExists(username)) {
            username = baseUsername + postfix;
            postfix++;
        }

        return username;
    }

    private boolean usernameExists(String username) {
        return traineeService.findAll().stream().anyMatch(t -> username.equals(t.getUsername())) ||
                trainerService.findAll().stream().anyMatch(t -> username.equals(t.getUsername()));
    }

    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
