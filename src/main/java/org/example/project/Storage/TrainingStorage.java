package org.example.project.Storage;

import org.example.project.Model.Training;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class TrainingStorage {

    private final Map<Long, Training> storage = new HashMap<>();

    public Map<Long, Training> getTrainingMap() {
        return storage;
    }
}
