package com.example.todo.service.tasks;

import com.example.todo.controller.tasks.TaskNotFoundException;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskEntity> find(TaskSearchEntity searchEntity) {
        return taskRepository.select();
    }

    public Optional<TaskEntity> findById(long id) {
        return taskRepository.selectById(id);
    }

    @Transactional
    public void create(TaskEntity newEntity) {
        taskRepository.insert(newEntity);
    }

    @Transactional
    public void update(TaskEntity entity) {
        findById(entity.id()).orElseThrow(TaskNotFoundException::new);
        taskRepository.update(entity);
    }

    @Transactional
    public void delete(long id) {
        var isDeleted = taskRepository.deleteById(id);
        if (!isDeleted) {
            throw new TaskNotFoundException();
        }
    }
}
