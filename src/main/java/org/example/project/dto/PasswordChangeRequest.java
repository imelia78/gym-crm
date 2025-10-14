package org.example.project.dto;

public record PasswordChangeRequest(
        String username,
        String oldPassword,
        String newPassword) {
}
