package com.example.todo.controller.tasks;

public record TaskForm(
        String summary,
        String description,
        String status
) {
}
