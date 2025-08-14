package org.example.project.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.project.credentials.CredentialsGenerator;
import org.example.project.model.Training;
import org.example.project.model.TrainingType;
import org.example.project.repository.TraineeRepository;
import org.example.project.repository.TrainerRepository;
import org.example.project.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingRepository trainingRepository;

    //  private  final CredentialsGenerator credentialsGenerator;


    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;

    }

    @Override
    public Training createTraining(Training training) {
        Training saved = trainingRepository.save(training);
        logger.info("Training created: " + saved);
        return saved;
    }

    @Override
    public List<Training> getAllTrainings() {
        logger.debug("Fetching all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByTrainee(Long traineeId) {
        logger.info("Fetching trainings for trainee ID: {}", traineeId);
        return trainingRepository.findByTraineeId(traineeId);
    }

    @Override
    public List<Training> getTrainingsByTrainer(Long trainerId) {
        logger.info("Fetching trainings for trainer ID: {}", trainerId);
        return trainingRepository.findByTrainerId(trainerId);
    }

    @Override
    public List<Training> getTrainingsByDate(LocalDate date) {
        logger.info("Fetching trainings by date: {}", date);
        return trainingRepository.findByDate(date);
    }

    @Override
    public List<Training> getTrainingsByType(TrainingType type) {
        logger.info("Fetching trainings by type: {}", type);
        return trainingRepository.findByType(type);
    }

    @Override
    public Training updateTraining(Training training) {
        logger.info("Updating training with ID: {}", training.getId());
        Training existing = trainingRepository.findById(training.getId())
                .orElseThrow(() -> {
                    logger.error("Training with ID {} not found for update", training.getId());
                    return new EntityNotFoundException("Training not found with ID " + training.getId());
                });
        existing.setTrainingName(training.getTrainingName());
        existing.setTrainingType(training.getTrainingType());
        existing.setTrainingDate(training.getTrainingDate());
        existing.setTrainingDuration(training.getTrainingDuration());
        existing.setTrainee(training.getTrainee());
        existing.setTrainer(training.getTrainer());
        return trainingRepository.update(training);
    }

    @Override
    public void deleteTraining(Long trainingId) {
        logger.info("Deleting training with ID: {}", trainingId);
        Training existing = trainingRepository.findById(trainingId)
                .orElseThrow(() -> {
                    logger.error("Training with ID {} not found for deletion", trainingId);
                    return new EntityNotFoundException("Training not found with ID " + trainingId);
                });
        trainingRepository.delete(existing);
        logger.info("Training with ID {} deleted successfully", trainingId);
    }

}
