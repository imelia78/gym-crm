package org.example.project.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.project.credentials.CredentialsGenerator;
import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.repository.TraineeRepository;
import org.example.project.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final CredentialsGenerator credentialsGenerator;

    private final Set<String> authenticatedUsers = new CopyOnWriteArraySet<>();


    public AuthService(TraineeRepository traineeRepository, TrainerRepository trainerRepository,
                       CredentialsGenerator credentialsGenerator) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.credentialsGenerator = credentialsGenerator;
    }


    public Trainee createTrainee(Trainee trainee) {
        trainee.setUsername(credentialsGenerator.generateUsername(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(credentialsGenerator.generatePassword());
        trainee.setFirstName(trainee.getFirstName());
        trainee.setLastName(trainee.getLastName());
        trainee.setIsActive(true);
        Trainee saved = traineeRepository.save(trainee);
        logger.info("Created new trainee with username: {}", saved.getUsername());
        return saved;
    }

    public Trainer createTrainer(Trainer trainer) {
        trainer.setUsername(credentialsGenerator.generateUsername(trainer.getFirstName(), trainer.getLastName()));
        trainer.setPassword(credentialsGenerator.generatePassword());
        trainer.setIsActive(true);
        trainer.setSpecialization(trainer.getSpecialization());
        trainer.setFirstName(trainer.getFirstName());
        trainer.setLastName(trainer.getLastName());
        Trainer saved = trainerRepository.save(trainer);
        logger.info("Created new trainee with username: {}", saved.getUsername());
        return saved;

    }


    public boolean isAuthenticated(String username) {
        return authenticatedUsers.contains(username);
    }

    public void logout(String username) {
        authenticatedUsers.remove(username);
        logger.info("User {} logged out", username);
    }


    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public boolean register(String username, String password) {
        Optional<Trainee> traineeOpt = traineeRepository.findByUsername(username);
        if (traineeOpt.isPresent() && traineeOpt.get().getPassword().equals(password)) {
            authenticatedUsers.add(username);
            logger.info("Authentication successful for trainee: {}", username);
            return true;
        }

        Optional<Trainer> trainerOpt = trainerRepository.findByUsername(username);
        if (trainerOpt.isPresent() && trainerOpt.get().getPassword().equals(password)) {
            authenticatedUsers.add(username);
            logger.info("Authentication successful for trainer: {}", username);
            return true;
        }

        logger.warn("Authentication failed for username: {}", username);
        return false;
    }

    /**
     * Change password for a user if the old password matches.
     */
    @Transactional
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Trainee trainee = traineeRepository.findByUsername(username).orElse(null);
        if (trainee != null && trainee.getPassword().equals(oldPassword)) {
            trainee.setPassword(newPassword);
            traineeRepository.update(trainee);
            logger.info("Password successfully updated for trainee: {}", username);
            return true;
        }

        Trainer trainer = trainerRepository.findByUsername(username).orElse(null);
        if (trainer != null && trainer.getPassword().equals(oldPassword)) {
            trainer.setPassword(newPassword);
            trainerRepository.update(trainer);
            logger.info("Password successfully updated for trainer: {}", username);
            return true;
        }

        logger.warn("Password update failed for username: {} (invalid old password)", username);
        return false;
    }

    /**
     * Reset password for a user and generate a new one.
     */
    @Transactional
    public String resetPassword(String username) {

        Optional<Trainee> traineeOpt = traineeRepository.findByUsername(username);
        if (traineeOpt.isPresent()) {
            Trainee trainee = traineeOpt.get();
            String newPassword = credentialsGenerator.generatePassword();
            trainee.setPassword(newPassword);
            traineeRepository.save(trainee);
            return newPassword;
        }

        Optional<Trainer> trainerOpt = trainerRepository.findByUsername(username);
        if (trainerOpt.isPresent()) {
            Trainer trainer = trainerOpt.get();
            String newPassword = credentialsGenerator.generatePassword();
            trainer.setPassword(newPassword);
            trainerRepository.save(trainer);
            return newPassword;
        }
        logger.error("Password reset failed. User not found: {}", username);
        throw new IllegalArgumentException("User not found: " + username);
    }


    @Transactional
    public boolean setActiveStatusForTrainee(String username, boolean isActive) {
        Trainee trainee = traineeRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Invalid username"));
        trainee.setIsActive(isActive);
        traineeRepository.update(trainee);
        logger.info("Trainee {} has been {}", username, isActive ? "activated" : "deactivated");
        return true;
    }


    @Transactional
    public boolean setActiveStatusForTrainer(String username, boolean isActive) {
        Trainer trainer = trainerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Invalid username"));
        trainer.setIsActive(isActive);
        trainerRepository.update(trainer);
        logger.info("Trainer {} has been {}", username, isActive ? "activated" : "deactivated");
        return true;
    }

    public Set<String> getAuthenticatedUsers() {
        return authenticatedUsers;
    }
}
