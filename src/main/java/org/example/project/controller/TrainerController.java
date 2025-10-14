package org.example.project.controller;
import org.example.project.model.Trainer;
import org.example.project.service.AuthService;
import org.example.project.service.TraineeService;
import org.example.project.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    private final AuthService authService;
    private final TrainerService trainerService;
    private final TraineeService traineeService;

    public TrainerController(TrainerService trainerService, TraineeService traineeService, AuthService authService) {
        this.trainerService = trainerService;
        this.authService = authService;
        this.traineeService = traineeService;
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers(@RequestParam(name = "username", required = false) String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Trainer> allTrainers = trainerService.findAll();
        return ResponseEntity.ok(allTrainers);


    }

    @GetMapping("/{username}")
    public ResponseEntity<Trainer> getTrainerByUsername(@PathVariable String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Trainer trainer = trainerService.findTrainerByUsername(username);
        return ResponseEntity.ok(trainer);
    }


    @PutMapping("/update-trainer-info")
    public ResponseEntity<Trainer> updateTrainer(@RequestBody Trainer trainer) {
        if (!authService.getAuthenticatedUsers().contains(trainer.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Trainer updated = trainerService.updateTrainerProfile(trainer.getUsername(), trainer);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete-trainer/{username}")
    public ResponseEntity<String> deleteTrainer(@PathVariable String username) {
        if (!authService.getAuthenticatedUsers().contains(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        trainerService.delete(username);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/not-assigned")
    public ResponseEntity<List<Trainer>> getNotAssignedTrainers(@RequestParam String traineeUsername) {
        if (!authService.getAuthenticatedUsers().contains(traineeUsername)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Trainer> notAssignedTrainers = traineeService.getNotAssignedTrainers(traineeUsername);
        return ResponseEntity.ok(notAssignedTrainers);

    }
}
