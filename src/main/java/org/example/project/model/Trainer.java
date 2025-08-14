package org.example.project.model;


import jakarta.persistence.*;
import org.example.project.credentials.CredentialsGenerator;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "trainers")
public class Trainer extends User {

    @Column(nullable = false)
    private String specialization;

    public Trainer() {
        super();
    }

    @ManyToMany
    @JoinTable(
            name = "trainer_trainee",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id")
    )
    private List<Trainee> trainees = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings = new ArrayList<>();


    public Trainer(CredentialsGenerator credentialsGenerator, String firstName, String lastName, boolean isActive, String specialization) {
        super(credentialsGenerator, firstName, lastName, isActive);

        this.specialization = specialization;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
