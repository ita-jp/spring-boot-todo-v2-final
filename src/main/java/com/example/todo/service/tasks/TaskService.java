package com.example.todo.service.tasks;

import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskEntity> find() {
        return taskRepository.select();
    }

    public Optional<TaskEntity> findById(long id) {
        return taskRepository.selectById(id);
    }

    public void create(TaskEntity newEntity) {
        taskRepository.insert(newEntity);
    }
}
