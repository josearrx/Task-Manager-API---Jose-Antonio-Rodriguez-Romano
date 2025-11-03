package com.hitss.springboot.taskmanager.repositories;

import com.hitss.springboot.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}