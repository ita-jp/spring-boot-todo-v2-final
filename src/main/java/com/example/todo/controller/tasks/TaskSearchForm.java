package com.example.todo.controller.tasks;

import java.util.List;

public record TaskSearchForm(
        String summary,
        List<String> status
) {
}
