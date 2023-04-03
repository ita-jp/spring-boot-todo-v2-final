package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService = new TaskService();

    @GetMapping
    public String index(Model model) {
        var dtoList = taskService.find();
        model.addAttribute("taskList", dtoList);
        return "tasks/list";
    }
}
