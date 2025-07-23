package org.example.project.Storage;

import org.example.project.Credentials.CredentialsGenerator;
import org.example.project.DAO.TraineeDAOImpl;
import org.example.project.DAO.TrainerDAOImpl;
import org.example.project.DAO.TrainingDAOImpl;
import org.example.project.FileReader.FileReaderUtil;
import org.example.project.Model.Trainee;
import org.example.project.Model.Trainer;
import org.example.project.Model.Training;
import org.example.project.Model.TrainingType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


@Component
public class DataInitializer implements InitializingBean {


    @Value("${trainee.data.file}")
    private Resource traineeFile;
    @Value("${trainer.data.file}")
    private Resource trainerFile;
    @Value("${training.data.file}")
    private Resource trainingFile;

    private final FileReaderUtil fileReaderUtil;

    private final TraineeDAOImpl traineeDAOImpl;
    private final TrainerDAOImpl trainerDAOImpl;
    private final TrainingDAOImpl trainingDAOImpl;
    private final CredentialsGenerator credentialsGenerator;

    public DataInitializer(FileReaderUtil fileReaderUtil, TraineeDAOImpl traineeDAOImpl, TrainerDAOImpl trainerDAOImpl, TrainingDAOImpl trainingDAOImpl, CredentialsGenerator credentialsGenerator) {
        this.fileReaderUtil = fileReaderUtil;
        this.traineeDAOImpl = traineeDAOImpl;
        this.trainerDAOImpl = trainerDAOImpl;
        this.trainingDAOImpl = trainingDAOImpl;
        this.credentialsGenerator = credentialsGenerator;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private void initTrainees() throws IOException {
        List<String> lines = fileReaderUtil.readLinesFromResource(traineeFile);
        for (String line : lines) {
            if (line.isBlank() || line.startsWith("#")) continue;
            Trainee trainee = parseTrainee(line);
            traineeDAOImpl.create(trainee);
        }
    }

    private void initTrainers() throws IOException {
        List<String> lines = fileReaderUtil.readLinesFromResource(trainerFile);
        for (String line : lines) {
            if (line.isBlank() || line.startsWith("#")) continue;
            Trainer trainer = parseTrainer(line);
            trainerDAOImpl.create(trainer);
        }
    }

    private void initTrainings() throws IOException {
        List<String> lines = fileReaderUtil.readLinesFromResource(trainingFile);
        for (String line : lines) {
            if (line.isBlank() || line.startsWith("#")) continue;
            Training training = parseTraining(line);
            trainingDAOImpl.create(training);
        }
    }

    private Trainee parseTrainee(String line) {
        String[] parts = line.split(";");
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid trainee line: " + line);
        }
        Trainee t = new Trainee();
        t.setUserId(Long.parseLong(parts[0]));
        t.setFirstName(parts[1]);
        t.setLastName(parts[2]);
        t.setDateOfBirth(LocalDate.parse(parts[3]));
        t.setAddress(parts[4]);
        t.setUsername(credentialsGenerator.generateUsername(t.getFirstName(), t.getLastName()));
        t.setPassword(credentialsGenerator.generatePassword());
        return t;
    }

    private Trainer parseTrainer(String line) {
        String[] parts = line.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid trainer line: " + line);
        }
        Trainer tr = new Trainer();
        tr.setUserId(Long.parseLong(parts[0]));
        tr.setFirstName(parts[1]);
        tr.setLastName(parts[2]);
        tr.setSpecialization(parts[3]);
        tr.setUsername(credentialsGenerator.generateUsername(tr.getFirstName(), tr.getLastName()));
        tr.setPassword(credentialsGenerator.generatePassword());
        return tr;
    }

    private Training parseTraining(String line) {
        String[] parts = line.split(";");
        if (parts.length < 7) {
            throw new IllegalArgumentException("Invalid training line: " + line);
        }
        Training training = new Training();
        training.setId(Long.parseLong(parts[0]));
        training.setTraineeId(Long.parseLong(parts[1]));
        training.setTrainerId(Long.parseLong(parts[2]));
        training.setTrainingName(parts[3]);
        training.setTrainingType(new TrainingType(parts[4]));
        training.setTrainingDate(LocalDate.parse(parts[5]));
        training.setTrainingDuration(Duration.ofDays(Integer.parseInt(parts[6])));
        return training;
    }

}