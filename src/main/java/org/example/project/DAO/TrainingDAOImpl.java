package org.example.project.DAO;

import org.example.project.Model.Training;
import org.example.project.Storage.TrainingStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainingDAOImpl implements TrainingDAO {

    private  Map<Long, Training> trainingMap;

    private static final Logger logger = LoggerFactory.getLogger(TrainingDAOImpl.class);

    @Autowired
    public void setTrainingStorage( TrainingStorage storage) {
        this.trainingMap = storage.getTrainingMap();
    }

    @Override
    public Training create(Training training) {
     trainingMap.put(training.getId(), training);
        logger.info("Training with id {} created", training.getId());
     return training;
    }

    @Override
    public Optional<Training> findById(Long id) { // Может стоит завернуть в Опшионал?
        return Optional.ofNullable(trainingMap.get(id));
    }

    @Override
    public List<Training> findAll() {
        return List.of();
    }
}
