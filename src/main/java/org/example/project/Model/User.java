package org.example.project.Model;


import org.example.project.Credentials.CredentialsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {


    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;


    public User(CredentialsGenerator credentialsGenerator, String firstName, String lastName, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = credentialsGenerator.generateUsername(firstName, lastName);
        this.password = credentialsGenerator.generatePassword();
        this.isActive = isActive;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, isActive);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;

    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }

}
