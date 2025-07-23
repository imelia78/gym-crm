package org.example.project.Service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    T saveEntity(T entity);

    T updateEntity(T entity);

    Optional<T> findById(Long id);

    void deleteEntity(Long id);

    List<T> findAll();
}
