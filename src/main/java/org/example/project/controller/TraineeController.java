package org.example.project.controller;


import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.service.AuthService;
import org.example.project.service.TraineeService;
import org.example.project.service.TrainerService;
import org.example.project.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/trainees")
public class TraineeController {

    private final TraineeService traineeService;
    private final AuthService authService;


    public TraineeController(TraineeService traineeService, AuthService authService) {
        this.traineeService = traineeService;
        this.authService = authService;
    }


    @GetMapping
    public ResponseEntity<List<Trainee>> getAllTrainers(@RequestParam(required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<Trainee> allTrainers = traineeService.findAll();
        return ResponseEntity.ok(allTrainers);
    }

    @GetMapping("{username}")
    public ResponseEntity<Trainee> getTraineeByUsername(@PathVariable String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Trainee trainee = traineeService.findByUsername(username);
        return ResponseEntity.ok(trainee);
    }

    @PutMapping("/update-trainee-info")
    public ResponseEntity<Trainee> updateTrainer(@RequestBody Trainee trainee) {
        if (!authService.getAuthenticatedUsers().contains(trainee.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Trainee updated = traineeService.updateTraineeProfile(trainee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete-trainee/{username}")
    public ResponseEntity<String> deleteTrainee(@PathVariable String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        traineeService.deleteByUsername(username);
        return ResponseEntity.ok("deleted successfully");
    }


}
