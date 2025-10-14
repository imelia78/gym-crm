package org.example.project.controller;


import jdk.jfr.Frequency;
import org.example.project.model.Training;
import org.example.project.model.TrainingType;
import org.example.project.service.AuthService;
import org.example.project.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingController {

    private final TrainingService trainingService;
    private final AuthService authService;


    public TrainingController(TrainingService trainingService, AuthService authService) {
        this.trainingService = trainingService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings(@RequestParam(name = "username",required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(trainingService.getAllTrainings());
    }

    @GetMapping("/trainee/{traineeId}")
    public ResponseEntity<List<Training>> getTrainingsByTrainee(@PathVariable Long traineeId, @RequestParam(name = "username",required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(trainingService.getTrainingsByTrainee(traineeId));
    }

    // ✅ Get trainings by trainerId
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<Training>> getTrainingsByTrainer(@PathVariable Long trainerId, @RequestParam(name = "username",required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(trainingService.getTrainingsByTrainer(trainerId));
    }

    @GetMapping("/by-types")
    public ResponseEntity<List<TrainingType>> getTrainingsByType(@RequestParam(name = "username", required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(trainingService.getAllTrainingTypes());
    }


    @PostMapping("/add-training")
    public ResponseEntity<Training> addTraining(@RequestBody Training training, @RequestParam(name = "username",required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Training created = trainingService.createTraining(training);
        return ResponseEntity.ok(training);
    }


}
