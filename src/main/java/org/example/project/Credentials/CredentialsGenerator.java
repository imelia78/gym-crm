package org.example.project.Credentials;


import org.example.project.DAO.GenericDAO;
import org.example.project.DAO.TraineeDAOImpl;
import org.example.project.DAO.TrainerDAO;
import org.example.project.DAO.TrainerDAOImpl;
import org.example.project.Model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CredentialsGenerator {


    private  TraineeDAOImpl traineeDAO;

    private TrainerDAOImpl trainerDAO;


    @Autowired
    private void setTraineeDaoImpl(TraineeDAOImpl traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Autowired
    private void setTrainerDaoImpl(TrainerDAOImpl traineeDAO) {
        this.trainerDAO = traineeDAO;
    }

    public TraineeDAOImpl getTraineeDAO() {
        return traineeDAO;
    }

    public TrainerDAOImpl getTrainerDAO() {
        return trainerDAO;
    }

    public  String generateUsername(String firstName, String lastName) {

        String baseUsername = firstName.trim() + "." + lastName.trim();
        String username = baseUsername;
        int postfix = 1;
        while (traineeDAO.findAll().stream().anyMatch(trainer -> trainer.getUsername().equals(baseUsername)) ||
                trainerDAO.findAll().stream().anyMatch(trainer -> trainer.getUsername().equals(baseUsername))) {
            username = baseUsername + postfix;
            postfix++;
        }
        return username;
    }


    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

}
