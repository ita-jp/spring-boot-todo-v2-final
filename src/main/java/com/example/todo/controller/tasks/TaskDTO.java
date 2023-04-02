package com.example.todo.controller.tasks;

public record TaskDTO(
        long id,
        String summary,
        String description,
        String status
) {
}
