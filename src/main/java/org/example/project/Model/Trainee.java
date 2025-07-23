package org.example.project.Model;

import org.example.project.Credentials.CredentialsGenerator;

import java.time.LocalDate;

public class Trainee extends User {
   private LocalDate dateOfBirth;
   private String address;
   private Long userId;

   public Trainee() {
       super();

   }

    public Trainee(CredentialsGenerator credentialsGenerator, String firstName, String lastName, boolean isActive, LocalDate dateOfBirth, String address, Long userId) {
        super(credentialsGenerator, firstName, lastName, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
