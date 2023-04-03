package com.example.todo.controller.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String index(Model model) {
        var task1 = new TaskDTO(1, "Spring Boot を学ぶ", "TODO アプリケーションを作る", "TODO");
        var task2 = new TaskDTO(2, "Spring Security を学ぶ", "ログイン機能を作る", "TODO");
        var dtoList = List.of(task1, task2);
        model.addAttribute("taskList", dtoList);
        return "tasks/list";
    }
}
