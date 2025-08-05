package org.example.project.Model;

import jakarta.persistence.*;
import org.example.project.Credentials.CredentialsGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainees")
public class Trainee extends User {

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String address;


    @ManyToMany(mappedBy = "trainees")
    private List<Trainer> trainers = new ArrayList<>();

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL, orphanRemoval = true)
    // orphanRemoval -- удалит дочерние обьекты без родителя!
    private List<Training> trainings = new ArrayList<>();


    public Trainee() {
        super();

    }


    public Trainee(CredentialsGenerator credentialsGenerator, String firstName, String lastName,
                   boolean isActive, LocalDate dateOfBirth, String address) {
        super(credentialsGenerator, firstName, lastName, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;

    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
