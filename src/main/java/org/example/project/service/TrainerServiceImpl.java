package org.example.project.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.project.credentials.CredentialsGenerator;
import org.example.project.model.Trainer;
import org.example.project.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private final TrainerRepository trainerRepository;
    private final CredentialsGenerator credentialsGenerator;



    public TrainerServiceImpl(TrainerRepository trainerRepository, CredentialsGenerator credentialsGenerator) {
        this.trainerRepository = trainerRepository;
        this.credentialsGenerator = credentialsGenerator;
    }




    @Override
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findTrainerByUsername(String username) {
        return trainerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
    }


    @Override
    public Trainer updateTrainerProfile(String username, Trainer updatedTrainer) {
        Trainer existing = findTrainerByUsername(username);
        existing.setFirstName(updatedTrainer.getFirstName());
        existing.setLastName(updatedTrainer.getLastName());
        existing.setUsername(updatedTrainer.getUsername());
        existing.setPassword(updatedTrainer.getPassword());
        logger.info("Updated trainer profile for trainer: {}", updatedTrainer.getUsername());
        return trainerRepository.update(existing);
    }

    @Override
    public void delete(String username) {
        Trainer trainer = trainerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        trainerRepository.delete(trainer);
    }

/*
    @Override
    public void changePassword(String username, String newPassword) {
        Trainer trainer = getTrainerByUsername(username);
        trainer.setPassword(newPassword);
        logger.info("Changed password for trainer: {}", trainer.getUsername());
        trainerRepository.update(trainer);
    }

    @Override
    public void activate(String username) {
        Trainer trainer = getTrainerByUsername(username);
        if (!trainer.getIsActive()) {
            trainer.setIsActive(true);
            trainerRepository.update(trainer);
        }
    }

    @Override
    public void deactivate(String username) {
        Trainer trainer = getTrainerByUsername(username);
        if (trainer.getIsActive()) {
            trainer.setIsActive(false);
            logger.info("trainer deactivated: {}", trainer.getUsername());
            trainerRepository.update(trainer);
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        return trainerRepository.findByUsername(username)
                .map(t -> t.getPassword().equals(password))
                .orElse(false);
    }*/

}
