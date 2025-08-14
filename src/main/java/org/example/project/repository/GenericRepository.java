package org.example.project.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

     Optional<T> findById(Long id);

     List<T> findAll();

     T save(T t);

     T update(T t);

     void delete(T t);


}
