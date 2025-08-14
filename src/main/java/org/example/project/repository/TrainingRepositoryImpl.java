package org.example.project.repository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.project.model.Training;
import org.example.project.model.TrainingType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class TrainingRepositoryImpl extends GenericRepositoryImpl<Training> implements TrainingRepository {

    public TrainingRepositoryImpl() {
        super();
        setEntityClass(Training.class);
    }


    @Override
    public List<Training> findByTraineeId(Long traineeId) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainee.id = :traineeId", Training.class);
        query.setParameter("traineeId", traineeId);
        return query.getResultList();
    }

    @Override
    public List<Training> findByTrainerId(Long trainerId) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainer.id = :trainerId", Training.class);
        query.setParameter("trainerId", trainerId);
        return query.getResultList();
    }

    @Override
    public List<Training> findByDate(LocalDate date) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainingDate = :date", Training.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Training> findByType(TrainingType type) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainingType = :type", Training.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
}
