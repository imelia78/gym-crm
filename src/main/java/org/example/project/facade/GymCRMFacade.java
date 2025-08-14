package org.example.project.facade;

import org.example.project.service.*;
import org.springframework.stereotype.Service;

public class GymCRMFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;


    public GymCRMFacade(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }


}
