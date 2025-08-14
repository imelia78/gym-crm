package org.example.project.repository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.project.model.Trainee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
class TraineeRepositoryImpl extends GenericRepositoryImpl<Trainee> implements TraineeRepository {

    public TraineeRepositoryImpl() {
        super();
        setEntityClass(Trainee.class);
    }

    @Override
    public Optional<Trainee> findByUsername(String username) {
        TypedQuery<Trainee> query = entityManager.createQuery(
                "SELECT t FROM Trainee t WHERE t.username = :username", Trainee.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst();
    }
}
