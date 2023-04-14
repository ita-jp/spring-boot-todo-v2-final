package com.example.todo.controller.tasks;

import java.util.List;

public record TaskSearchDTO(
        String summary,
        List<String> statusList
) {
}
