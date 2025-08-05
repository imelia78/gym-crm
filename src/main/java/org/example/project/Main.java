package org.example.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.project.Config.HibernateConfig;
import org.example.project.Credentials.CredentialsGenerator;
import org.example.project.Model.Trainer;
import org.example.project.Model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        CredentialsGenerator generator = context.getBean(CredentialsGenerator.class);

    }
}