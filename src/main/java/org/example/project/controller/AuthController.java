package org.example.project.controller;


import org.example.project.credentials.CredentialsGenerator;
import org.example.project.dto.Credentials;
import org.example.project.dto.PasswordChangeRequest;
import org.example.project.dto.StatusChangeRequest;
import org.example.project.model.Trainee;
import org.example.project.model.Trainer;
import org.example.project.service.AuthService;
import org.example.project.service.TraineeService;
import org.example.project.service.TrainerService;
import org.example.project.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")

public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    //without credentials
    @PostMapping("/trainee-reg")
    public ResponseEntity<Trainee> registerTrainee(@RequestBody Trainee trainee) {
        Trainee registered = authService.createTrainee(trainee);
        return ResponseEntity.ok(registered);
    }

    //without credentials
    @PostMapping("/trainer-reg")
    public ResponseEntity<Trainer> registerTrainer(@RequestBody Trainer trainer) {
        Trainer registered = authService.createTrainer(trainer);
        return ResponseEntity.ok(registered);
    }

    //without credentials
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        boolean authenticated = authService.register(credentials.username(), credentials.password());
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request) {
        if (!authService.isAuthenticated(request.username())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please, pass authentication -> /login !");
        }
        if (!authService.isAuthenticated(request.username())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Not authenticated");
        }
        boolean changed = authService.changePassword(request.username(), request.oldPassword(), request.newPassword());
        if (changed) {
            authService.logout(request.username());
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or old password");
        }
    }


    @PatchMapping("/trainees/{username}/status")
    public ResponseEntity<String> changeStatusForTrainee(@PathVariable("username") String username, @RequestBody StatusChangeRequest request) {
        if (authService.isAuthenticated(request.username())) {
            boolean changed = authService.setActiveStatusForTrainee(username, request.isActive());
            if (changed) {
                return ResponseEntity.ok("Trainee " + username + " is now " + (request.isActive() ? "activated" : "DE-activated"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or old password");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or old password");
    }


    @PatchMapping("/trainers/{username}/status")
    public ResponseEntity<String> changeStatusForTrainer(@PathVariable("username") String username, @RequestBody StatusChangeRequest request) {
        if (authService.isAuthenticated(request.username())) {
            boolean changed = authService.setActiveStatusForTrainer(username, request.isActive());
            if (changed) {
                return ResponseEntity.ok("Trainer " + username + " is now " + (request.isActive() ? "activated" : "DE-activated"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or old password");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or old password");
    }
}
