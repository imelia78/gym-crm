package org.example.project.DAO;

import org.example.project.Model.Trainer;
import org.example.project.Storage.TrainerStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class TrainerDAOImpl implements TrainerDAO {


    private Map<Long, Trainer> trainerMap;

    private static final Logger logger = LoggerFactory.getLogger(TrainerDAOImpl.class);


    @Autowired
    public void setTrainerStorage(TrainerStorage storage) {
        this.trainerMap = storage.getStorage();
    }


    @Override
    public Trainer create(Trainer trainer) {
        trainerMap.put(trainer.getUserId(), trainer);
        logger.info("Trainer created " + trainer.getFirstName() + " " + trainer.getLastName());
        return trainer;
    }

    @Override
    public Trainer update(Trainer trainer) {
        long id = trainer.getUserId();
        if (trainerMap.containsKey(id)) {
            trainerMap.put(id, trainer);
            logger.info("Updated trainer with id={}", id);
        } else {
            logger.warn("Update failed, trainer with id={} not found", id);
        }
        return trainer;
    }

    @Override
    public void delete(Long id) {
        trainerMap.remove(id);
        logger.info("Deleted trainer with id={}", id);
    }


    @Override
    public Optional<Trainer> findById(Long id) {
        return Optional.ofNullable(trainerMap.get(id));
    }

    @Override
    public List<Trainer> findAll() {
        return ((List<Trainer>) trainerMap.values());
    }
}
