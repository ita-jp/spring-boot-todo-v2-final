package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskStatus;

public record TaskForm(
        String summary,
        String description,
        String status
) {

    public TaskEntity toEntity() {
        return new TaskEntity(
                null,
                summary,
                description,
                TaskStatus.valueOf(status)
        );
    }
}
