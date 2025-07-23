package org.example.project.Service;

import org.example.project.DAO.TrainingDAO;
import org.example.project.Model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl {
    private TrainingDAO trainingDAO;
    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }


    public void createTraining(Training training){
        trainingDAO.create(training);
        logger.info("Training with training name {} has been created", training.getTrainingName());

    }

    public Optional<Training> getTraining(Long id){
        return trainingDAO.findById(id);
    }

    public List<Training> getAllTrainings(){
        return trainingDAO.findAll();
    }
}
