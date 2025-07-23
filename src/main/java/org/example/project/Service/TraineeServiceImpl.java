package org.example.project.Service;

import org.example.project.DAO.TraineeDAO;
import org.example.project.Model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements GenericService<Trainee> {
    private TraineeDAO traineeDAO;

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Override
    public Trainee saveEntity(Trainee entity) {
        traineeDAO.create(entity);
        logger.info("Registered Trainee: " + entity);
        return entity;
    }

    @Override
    public Trainee updateEntity(Trainee entity) {
        traineeDAO.update(entity);
        logger.info("Updated Trainee: " + entity);
        return null;
    }

    @Override
    public Optional<Trainee> findById(Long id) {
        return traineeDAO.findById(id);
    }

    @Override
    public void deleteEntity(Long id) {
        traineeDAO.delete(id);
        logger.info("Deleted Trainee with id {} ");
    }

    @Override
    public List<Trainee> findAll() {
        logger.info("Finding all Trainees");
        return traineeDAO.findAll();
    }


}
