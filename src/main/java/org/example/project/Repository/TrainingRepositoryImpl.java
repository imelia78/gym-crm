package org.example.project.Repository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.project.Model.Training;
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
    public List<Training> findByTraineeUsernameAndCriteria(String username, LocalDate fromDate, LocalDate toDate,
                                                           String trainerName, String trainingTypeName) {
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT t FROM Training t WHERE t.trainee.user.username = :username"
        );

        if (fromDate != null) queryBuilder.append(" AND t.trainingDate >= :fromDate");
        if (toDate != null) queryBuilder.append(" AND t.trainingDate <= :toDate");
        if (trainerName != null && !trainerName.isBlank())
            queryBuilder.append(" AND t.trainer.user.username = :trainerName");
        if (trainingTypeName != null && !trainingTypeName.isBlank())
            queryBuilder.append(" AND t.trainingType.name = :trainingTypeName");

        TypedQuery<Training> query = entityManager.createQuery(queryBuilder.toString(), Training.class);
        query.setParameter("username", username);
        if (fromDate != null) query.setParameter("fromDate", fromDate);
        if (toDate != null) query.setParameter("toDate", toDate);
        if (trainerName != null && !trainerName.isBlank()) query.setParameter("trainerName", trainerName);
        if (trainingTypeName != null && !trainingTypeName.isBlank())
            query.setParameter("trainingTypeName", trainingTypeName);

        return query.getResultList();
    }

    @Override
    public List<Training> findByTrainerUsernameAndCriteria(String username, LocalDate fromDate, LocalDate toDate, String traineeName) {
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT t FROM Training t WHERE t.trainer.user.username = :username"
        );
        if (fromDate != null) queryBuilder.append(" AND t.trainingDate >= :fromDate");
        if (toDate != null) queryBuilder.append(" AND t.trainingDate <= :toDate");
        if (traineeName != null && !traineeName.isBlank())
            queryBuilder.append(" AND t.trainee.user.username = :traineeName");

        TypedQuery<Training> query = entityManager.createQuery(queryBuilder.toString(), Training.class);
        query.setParameter("username", username);
        if (fromDate != null) query.setParameter("fromDate", fromDate);
        if (toDate != null) query.setParameter("toDate", toDate);
        if (traineeName != null && !traineeName.isBlank()) query.setParameter("traineeName", traineeName);

        return query.getResultList();
    }
}
