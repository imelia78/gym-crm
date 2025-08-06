package org.example.project.Credentials;


import org.example.project.Repository.TraineeRepository;
import org.example.project.Repository.TrainerRepository;
import org.example.project.Service.TraineeService;
import org.example.project.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CredentialsGenerator {


    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;


    public CredentialsGenerator(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
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
        return  traineeRepository.findAll().stream().anyMatch(t -> username.equals(t.getUsername())) ||
                trainerRepository.findAll().stream().anyMatch(t -> username.equals(t.getUsername()));
    }

    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
