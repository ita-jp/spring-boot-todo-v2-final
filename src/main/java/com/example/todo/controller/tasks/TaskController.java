package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public String showCreationForm(@ModelAttribute TaskForm form, Model model) {
        model.addAttribute("formControl", FormControl.forCreate());
        return "tasks/form";
    }

    @PostMapping
    public String create(@Validated TaskForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, model);
        }
        taskService.create(form.toEntity());
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        var form = taskService.findById(id)
                .map(TaskForm::fromEntity)
                .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("taskForm", form);
        model.addAttribute("formControl", FormControl.forUpdate(id));
        return "tasks/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") long id, @Validated @ModelAttribute TaskForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formControl", FormControl.forUpdate(id));
            return "tasks/form";
        }

        taskService.update(form.toEntity(id));

        return "redirect:/tasks/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    public record FormControl(
            String formMethod,
            String formAction,
            String submitLabel,
            String backURL
    ) {
        public static FormControl forCreate() {
            return new FormControl("post", "/tasks", "作成", "/tasks");
        }

        public static FormControl forUpdate(long id) {
            return new FormControl("put", "/tasks/" + id, "更新", "/tasks/" + id);
        }
    }

}
