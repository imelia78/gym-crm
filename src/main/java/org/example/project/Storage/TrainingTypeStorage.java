package org.example.project.Storage;


import org.example.project.Model.TrainingType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrainingTypeStorage {

    private final Map<Long, TrainingType> storage = new HashMap<>();

    public Map<Long, TrainingType> getStorage() {
        return storage;
    }

}
