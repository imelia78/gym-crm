package org.example.project.service;

import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.model.Training;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TraineeService {

    Trainee findById(Long id);                              // Получение по ID

    List<Trainee> findAll();                                // Все ученики

    Trainee updateTraineeProfile(Trainee trainee);                        // Обновление профиля

    Trainee findByUsername(String username);                // Поиск по username// Удаление (по объекту)

    void deleteByUsername(String username);                 // Жёсткое удаление

    List<Trainer> getNotAssignedTrainers(String traineeUsername); // Тренеры, не назначенные на ученика

    void updateTrainersList(String traineeUsername, List<Long> trainerIds); // Обновление списка тренеров

}
