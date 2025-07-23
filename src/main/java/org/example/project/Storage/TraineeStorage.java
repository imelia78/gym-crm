package org.example.project.Storage;

import org.example.project.Model.Trainee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TraineeStorage {

    private final Map<Long, Trainee> storage = new HashMap<>();

    public Map<Long, Trainee> getStorage() {
        return storage;
    }


}
