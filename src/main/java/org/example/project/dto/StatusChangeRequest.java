package org.example.project.dto;

public record StatusChangeRequest(
        String username,
        String password,
        boolean isActive) {
}
