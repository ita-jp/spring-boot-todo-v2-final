package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskSearchEntity;
import com.example.todo.service.tasks.TaskStatus;

import java.util.List;
import java.util.Optional;

public record TaskSearchForm(
        String summary,
        List<String> status
) {
    public TaskSearchEntity toEntity() {
        var statusEntityList = Optional.ofNullable(status)
                .map(statusList -> statusList.stream().map(TaskStatus::valueOf).toList())
                .orElse(List.of());
        return new TaskSearchEntity(summary, statusEntityList);
    }
}
