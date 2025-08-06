package org.example.project.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.project.Credentials.CredentialsGenerator;
import org.example.project.Model.Trainee;
import org.example.project.Model.Trainer;
import org.example.project.Model.Training;
import org.example.project.Repository.TraineeRepository;
import org.example.project.Repository.TrainerRepository;
import org.example.project.Repository.TrainingRepository;
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
    private final TrainingRepository trainingRepository;
    private final CredentialsGenerator credentialsGenerator;

    public TraineeServiceImpl(
            TraineeRepository traineeRepository,
            TrainerRepository trainerRepository,
            TrainingRepository trainingRepository,
            CredentialsGenerator credentialsGenerator
    ) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        this.credentialsGenerator = credentialsGenerator;
    }

    @Override
    public Trainee save(Trainee trainee) {
        trainee.setUsername(credentialsGenerator.generateUsername(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(credentialsGenerator.generatePassword());
        trainee.setIsActive(true);
        Trainee saved = traineeRepository.save(trainee);
        logger.info("Created new trainee with username: {}", saved.getUsername());
        return saved;
    }

    @Override
    public Trainee findById(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        return trainee.orElseThrow( () -> new EntityNotFoundException("Trainee not found by id: " + id));
    }

    @Override
    public List<Trainee> findAll() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee update(Trainee trainee) {
        return traineeRepository.update(trainee);
    }

    @Override
    public void delete(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        traineeRepository.delete(trainee.orElseThrow( () -> new EntityNotFoundException("Trainee not found by id: " + id)));
        logger.info("Deleted trainee by id: {}", id);
    }

    @Override
    public Trainee authenticate(String username, String password) {
        Optional<Trainee> trainee = traineeRepository.findByUsername(username);
        if (trainee.isEmpty() || !trainee.get().getPassword().equals(password)) {
            throw new SecurityException("Invalid username or password");
        }
        return trainee.orElse(null);
    }

    @Override
    public Trainee findByUsername(String username) {
        Optional<Trainee> trainee = traineeRepository.findByUsername(username);
        if (trainee.isEmpty()) throw new EntityNotFoundException("Trainee not found by username: " + username);
        return trainee.orElse(null);
    }

    @Override
    public Trainee changePassword(String username, String newPassword) {
        Trainee trainee = findByUsername(username);
        trainee.setPassword(newPassword);
        return traineeRepository.update(trainee);
    }

    @Override
    public Trainee activate(String username) {
        Trainee trainee = findByUsername(username);
        trainee.setIsActive(true);
        return traineeRepository.update(trainee);
    }

    @Override
    public Trainee deactivate(String username) {
        Trainee trainee = findByUsername(username);
        trainee.setIsActive(false);
        return traineeRepository.update(trainee);
    }

    @Override
    public void deleteByUsername(String username) {
        Trainee trainee = findByUsername(username);
        traineeRepository.delete(trainee);
        logger.info("Hard deleted trainee by username: {}", username);
    }

    @Override
    public List<Training> getTrainingsByCriteria(String username, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingTypeName) {
        return trainingRepository.findByTraineeUsernameAndCriteria(
                username, fromDate, toDate, trainerName, trainingTypeName
        );
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
                .map(Optional::get)            // достаём объект Trainer из Optional
                .toList();
        trainee.setTrainers(selectedTrainers);
        traineeRepository.update(trainee);
        logger.info("Updated trainers for trainee: {}", traineeUsername);
    }
}
