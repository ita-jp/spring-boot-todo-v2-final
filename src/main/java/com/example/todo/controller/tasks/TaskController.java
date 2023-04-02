package com.example.todo.controller.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String index(Model model) {
        var task = new TaskDTO(1, "Spring Boot を学ぶ", "TODO アプリケーションを作る", "TODO");
        model.addAttribute("task", task);
        return "tasks/list";
    }
}
