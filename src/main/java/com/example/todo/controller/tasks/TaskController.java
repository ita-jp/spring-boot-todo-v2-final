package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String index(Model model) {
        var dtoList = taskService.find()
                .stream()
                .map(TaskDTO::toDTO)
                .toList();
        model.addAttribute("taskList", dtoList);
        return "tasks/list";
    }

    @GetMapping("{id}")
    public String showDetail(@PathVariable("id") long id) {
        return "tasks/detail";
    }
}
