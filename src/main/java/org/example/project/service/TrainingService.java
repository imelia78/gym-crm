package org.example.project.service;

import org.example.project.model.Training;
import org.example.project.model.TrainingType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TrainingService {

    Training createTraining(Training training);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByTrainee(Long traineeId);

    List<Training> getTrainingsByTrainer(Long trainerId);

    List<Training> getTrainingsByDate(LocalDate date);

    List<Training> getTrainingsByType(TrainingType type);

    Training updateTraining(Training training);

    void deleteTraining(Long trainingId);



}
