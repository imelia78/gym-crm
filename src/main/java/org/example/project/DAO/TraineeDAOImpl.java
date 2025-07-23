package org.example.project.DAO;

import org.example.project.Model.Trainee;
import org.example.project.Storage.TraineeStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TraineeDAOImpl implements TraineeDAO {

    private static final Logger logger = LoggerFactory.getLogger(TraineeDAOImpl.class);

    private Map<Long, Trainee> traineeMap;

    @Autowired
    public void setTraineeStorage(TraineeStorage storage) {
        this.traineeMap = storage.getStorage();
    }

    @Override
    public Trainee create(Trainee trainee) {
        traineeMap.put(trainee.getUserId(), trainee);
        logger.info("Trainee created " + trainee.getFirstName() + " " + trainee.getLastName());
        return trainee;

    }

    @Override
    public Trainee update(Trainee trainee) {
        long id = trainee.getUserId();
        if (traineeMap.containsKey(id)) {
           traineeMap.put(id, trainee);
           logger.info("Updated trainee with id={}", id);
        } else{
            logger.warn("Update failed, trainee with id={} not found", id);
        }
        return trainee;
    }

    @Override
    public void delete(Long id) {
        traineeMap.remove(id);
        logger.info("Trainee with id={} deleted", id);
    }

    @Override
    public Optional<Trainee> findById(Long id) {
        return Optional.ofNullable(traineeMap.get(id));
    }

    @Override
    public List<Trainee> findAll() {
        return List.of((Trainee) traineeMap.values());
    }
}
