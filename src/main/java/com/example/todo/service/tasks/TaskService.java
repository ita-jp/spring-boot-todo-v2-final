package com.example.todo.service.tasks;

import java.util.List;

public class TaskService {

    public List<TaskEntity> find() {
        var task1 = new TaskEntity(1L, "Spring Boot を学ぶ", "TODO アプリケーションを作る", TaskStatus.DONE);
        var task2 = new TaskEntity(2L, "Spring Security を学ぶ", "ログイン機能を作る", TaskStatus.DONE);
        return List.of(task1, task2);
    }
}
