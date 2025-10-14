package org.example.project.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.project.credentials.CredentialsGenerator;
import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.model.Training;
import org.example.project.repository.TraineeRepository;
import org.example.project.repository.TrainerRepository;
import org.example.project.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final CredentialsGenerator credentialsGenerator;

    public TraineeServiceImpl(
            TraineeRepository traineeRepository,
            TrainerRepository trainerRepository,
            CredentialsGenerator credentialsGenerator
    ) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;

        this.credentialsGenerator = credentialsGenerator;
    }




    @Override
    public Trainee findById(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        return trainee.orElseThrow(() -> new EntityNotFoundException("Trainee not found by id: " + id));
    }

    @Override
    public List<Trainee> findAll() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee updateTraineeProfile(Trainee trainee) {
        logger.info("Updating trainee with ID: {}", trainee.getId());
        Trainee existing = traineeRepository.findById(trainee.getId())
                .orElseThrow(() -> {
                    logger.error("Trainee with ID {} not found for update", trainee.getId());
                    return new EntityNotFoundException("Trainee not found with ID " + trainee.getId());
                });
        existing.setFirstName(trainee.getFirstName());
        existing.setLastName(trainee.getLastName());
        existing.setDateOfBirth(trainee.getDateOfBirth());
        existing.setAddress(trainee.getAddress());
        existing.setIsActive(trainee.getIsActive());
        existing.setTrainings(trainee.getTrainings());
        existing.setTrainers(trainee.getTrainers());
        return traineeRepository.update(existing);
    }

/*
    @Override
    public void delete(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        traineeRepository.delete(trainee.orElseThrow(() -> new EntityNotFoundException("Trainee not found by id: " + id)));
        logger.info("Deleted trainee by id: {}", id);
    }
*/

    @Override
    public Trainee findByUsername(String username) {
        Optional<Trainee> trainee = traineeRepository.findByUsername(username);
        if (trainee.isEmpty()) throw new EntityNotFoundException("Trainee not found by username: " + username);
        return trainee.orElseThrow(() -> new EntityNotFoundException("Trainee not found by username: " + username));
    }



    @Override
    public void deleteByUsername(String username) {
        Trainee trainee = findByUsername(username);
        traineeRepository.delete(trainee);
        logger.info("Hard deleted trainee by username: {}", username);
    }


    @Override
    public List<Trainer> getNotAssignedTrainers(String traineeUsername) {
        Trainee trainee = findByUsername(traineeUsername);
        List<Trainer> allTrainers = trainerRepository.findAll();
        allTrainers.removeAll(trainee.getTrainers());
        return allTrainers;
    }

    @Override
    public void updateTrainersList(String traineeUsername, List<Long> trainerIds) {
        Trainee trainee = findByUsername(traineeUsername);
        List<Trainer> selectedTrainers = trainerIds.stream()
                .map(trainerRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        trainee.setTrainers(selectedTrainers);
        traineeRepository.update(trainee);
        logger.info("Updated trainers for trainee: {}", traineeUsername);
    }
}
