package org.example.project.Storage;

import org.example.project.Model.Trainer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class TrainerStorage {

    private final Map<Long, Trainer> storage = new HashMap<>();

    public Map<Long, Trainer> getStorage() {
        return storage;
    }
}
