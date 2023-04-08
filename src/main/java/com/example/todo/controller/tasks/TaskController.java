package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String showDetail(@PathVariable("id") long id, Model model) {
        var taskEntity = taskService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        model.addAttribute("task", TaskDTO.toDTO(taskEntity));
        return "tasks/detail";
    }

    @GetMapping("/creationForm")
    public String showCreationForm() {
        return "tasks/form";
    }

    @PostMapping
    public String create(TaskForm form, Model model) {
        taskService.create(form.toEntity());
        return index(model);
    }

}
