package com.example.todo.service.tasks;

public record TaskEntity(
        Long id,
        String summary,
        String description,
        TaskStatus status
) {
}
