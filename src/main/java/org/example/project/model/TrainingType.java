package org.example.project.model;


import jakarta.persistence.*;

@Entity
@Table(name = "training_types")
public class TrainingType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String trainingTypeName;




    public TrainingType(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }

    public TrainingType() {

    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
