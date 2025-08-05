package org.example.project.Repository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.project.Model.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class TrainerRepositoryImpl extends GenericRepositoryImpl<Trainer> implements TrainerRepository {

    public TrainerRepositoryImpl() {
        super();
        setEntityClass(Trainer.class);
    }

    @Override
    public Optional<Trainer> findByUsername(String username) {
        TypedQuery<Trainer> query = entityManager.createQuery(
                "SELECT t FROM Trainer t WHERE t.username = :username", Trainer.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst();
    }
}
