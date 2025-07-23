package org.example.project.Model;


import org.example.project.Credentials.CredentialsGenerator;

public class Trainer extends User {
    private Long userId;
    private String specialization;

    public Trainer() {
        super();
    }


    public Trainer(CredentialsGenerator credentialsGenerator, String firstName, String lastName, boolean isActive, Long userId, String specialization) {
        super(credentialsGenerator, firstName, lastName, isActive);
        this.userId = userId;
        this.specialization = specialization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return  super.toString() + "\nuserId: " + userId + "\nspecialization: " + specialization;
    }
}
