package org.example.project.DAO;

import org.example.project.Model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDAO  {
    Training create(Training training);
    Optional<Training> findById(Long id);
    List<Training> findAll();

}
