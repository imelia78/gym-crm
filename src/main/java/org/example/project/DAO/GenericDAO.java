package org.example.project.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T create(T entity);

    T update(T entity);

    void delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();
}
