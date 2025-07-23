package org.example.project.Service;

import org.example.project.DAO.TrainerDAO;
import org.example.project.Model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements GenericService<Trainer> {

    private TrainerDAO trainerDAO;
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Override
    public Trainer saveEntity(Trainer entity) {
        trainerDAO.create(entity);
        logger.info("Registered Trainer: " + entity);
        return entity;
    }

    @Override
    public Trainer updateEntity(Trainer entity) {
        trainerDAO.update(entity);
        logger.info("Updated Trainer: " + entity);
        return entity;
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return trainerDAO.findById(id);
    }

    @Override
    public void deleteEntity(Long id) {
        trainerDAO.delete(id);
        logger.info("Deleted Trainer with id{} ", id);


    }

    @Override
    public List<Trainer> findAll() {
        return trainerDAO.findAll();
    }

}
