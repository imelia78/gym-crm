package org.example.project.repository;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.example.project.model.TrainingType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class TrainingTypeRepositoryImpl extends  GenericRepositoryImpl<TrainingType> implements TrainingTypeRepository {
    public TrainingTypeRepositoryImpl() {
        super();
        setEntityClass(TrainingType.class);
    }

    @Override
    public Optional<TrainingType> findByTrainingTypeName(String name) {
        try {
            TrainingType result = entityManager
                    .createQuery("SELECT t FROM TrainingType t WHERE t.trainingTypeName = :name", TrainingType.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
