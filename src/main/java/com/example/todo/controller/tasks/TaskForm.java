package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TaskForm(
        @NotBlank
        @Size(max = 256, message = "256文字以内で入力してください")
        String summary,
        String description,
        @NotBlank
        @Pattern(regexp = "TODO|DOING|DONE", message = "Todo, Doing, Done のいずれかを指定してください")
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
