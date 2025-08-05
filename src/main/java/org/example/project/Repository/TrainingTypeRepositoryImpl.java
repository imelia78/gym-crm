package org.example.project.Repository;

import jakarta.transaction.Transactional;
import org.example.project.Model.TrainingType;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TrainingTypeRepositoryImpl extends  GenericRepositoryImpl<TrainingType> implements TrainingTypeRepository {
    public TrainingTypeRepositoryImpl() {
        super();
        setEntityClass(TrainingType.class);
    }
}
