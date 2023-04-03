package com.example.todo.service.tasks;

import com.example.todo.controller.tasks.TaskDTO;

import java.util.List;

public class TaskService {

    public List<TaskDTO> find() {
        var task1 = new TaskDTO(1, "Spring Boot を学ぶ", "TODO アプリケーションを作る", "TODO");
        var task2 = new TaskDTO(2, "Spring Security を学ぶ", "ログイン機能を作る", "TODO");
        return List.of(task1, task2);
    }
}
