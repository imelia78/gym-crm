package org.example.project.repository;

import jakarta.transaction.Transactional;
import org.example.project.model.TrainingType;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TrainingTypeRepositoryImpl extends  GenericRepositoryImpl<TrainingType> implements TrainingTypeRepository {
    public TrainingTypeRepositoryImpl() {
        super();
        setEntityClass(TrainingType.class);
    }
}
