package com.hitss.springboot.taskmanager.services.impl;

import com.hitss.springboot.taskmanager.models.Task;
import com.hitss.springboot.taskmanager.repositories.TaskRepository;
import com.hitss.springboot.taskmanager.services.TaskService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Optional<Task> update(Long id, Task task) {
        return taskRepository.findById(id).map(taskDb -> {
            taskDb.setTitle(task.getTitle());
            taskDb.setDescription(task.getDescription());
            taskDb.setCompleted(task.getCompleted());
            return taskRepository.save(taskDb);
        });
    }

    @Override
    @Transactional
    public Optional<Task> delete(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        optional.ifPresent(taskRepository::delete);
        return optional;
    }
}