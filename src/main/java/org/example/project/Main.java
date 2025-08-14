package org.example.project;

import org.example.project.config.HibernateConfig;
import org.example.project.credentials.CredentialsGenerator;
import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.model.Training;
import org.example.project.model.TrainingType;
import org.example.project.repository.TrainingTypeRepository;
import org.example.project.service.TraineeService;
import org.example.project.service.TrainerService;
import org.example.project.service.TrainingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Получаем сервисы напрямую
        CredentialsGenerator generator = context.getBean(CredentialsGenerator.class);
        TraineeService traineeService = context.getBean(TraineeService.class);
        TrainerService trainerService = context.getBean(TrainerService.class);
        TrainingService trainingService = context.getBean(TrainingService.class);
        TrainingTypeRepository trainingTypeRepository = context.getBean(TrainingTypeRepository.class);
        // Создание Trainee
        Trainee trainee = new Trainee();
        trainee.setFirstName("Ivan");
        trainee.setLastName("Ivanov");
        trainee.setUsername(generator.generateUsername(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(generator.generatePassword());
        trainee.setIsActive(true);
        trainee.setAddress("Tbilisi, Saburtalo");
        trainee.setDateOfBirth(LocalDate.of(2001, 4, 18));

        // Сохраняем Trainee
        traineeService.createTrainee(trainee);


        Trainer trainer = new Trainer();
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setUsername(generator.generateUsername(trainer.getFirstName(), trainer.getLastName()));
        trainer.setPassword(generator.generatePassword());
        trainer.setSpecialization("Cardio");
        trainer.setIsActive(true);


        trainerService.createTrainer(trainer);

        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName("Cardio");
        trainingTypeRepository.save(trainingType);



        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName("Morning Cardio");


        training.setTrainingType(trainingType);
        training.setTrainingDate(LocalDate.now());
        training.setTrainingDuration(60);

        // Сохраняем Training
        trainingService.createTraining(training);

        System.out.println("Trainee, Trainer и Training successfully created");
    }


}