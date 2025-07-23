package org.example.project.Facade;

import org.example.project.Service.TraineeServiceImpl;
import org.example.project.Service.TrainerServiceImpl;

public class GymCRMFacade {
    private final TraineeServiceImpl traineeServiceImpl;
    private final TrainerServiceImpl trainerServiceImpl;

    public GymCRMFacade(TraineeServiceImpl traineeServiceImpl, TrainerServiceImpl trainerServiceImpl) {
        this.traineeServiceImpl = traineeServiceImpl;
        this.trainerServiceImpl = trainerServiceImpl;
    }
}
