package org.example.project.Service;

import org.example.project.Model.Trainee;
import org.example.project.Model.Trainer;
import org.example.project.Model.Training;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TraineeService {


    Trainee save(Trainee trainee);                          // Создание профиля

    Trainee findById(Long id);                              // Получение по ID

    List<Trainee> findAll();                                // Все ученики

    Trainee update(Trainee trainee);                        // Обновление профиля

    void delete(Long id);                           // Удаление (по объекту)

    Trainee authenticate(String username, String password); // Аутентификация

    Trainee findByUsername(String username);                // Поиск по username

    Trainee changePassword(String username, String newPassword); // Изменение пароля

    Trainee activate(String username);                      // Активация

    Trainee deactivate(String username);                    // Деактивация

    void deleteByUsername(String username);                 // Жёсткое удаление

    List<Training> getTrainingsByCriteria(
            String username,
            LocalDate fromDate,
            LocalDate toDate,
            String trainerName,
            String trainingTypeName
    );

    List<Trainer> getNotAssignedTrainers(String traineeUsername); // Тренеры, не назначенные на ученика

    void updateTrainersList(String traineeUsername, List<Long> trainerIds); // Обновление списка тренеров


}
