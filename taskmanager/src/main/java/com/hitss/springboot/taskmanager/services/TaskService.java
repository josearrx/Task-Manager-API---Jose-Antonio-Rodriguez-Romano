package com.hitss.springboot.taskmanager.services;

import com.hitss.springboot.taskmanager.models.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    Optional<Task> update(Long id, Task task);
    Optional<Task> delete(Long id);
}