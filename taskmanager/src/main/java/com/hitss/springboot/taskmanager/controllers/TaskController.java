package com.hitss.springboot.taskmanager.controllers;

import com.hitss.springboot.taskmanager.models.Task;
import com.hitss.springboot.taskmanager.services.TaskService;
import com.hitss.springboot.taskmanager.utils.UtilCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tareas", description = "")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Listar todas las tareas -> ROLE_USER y ROLE_ADMIN")
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Task> list() {
        return taskService.findAll();
    }

    @Operation(summary = "Listar una tarea por Id -> ROLE_USER y ROLE_ADMIN")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findById(id);
        return taskOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva tarea -> ROLE_USER y ROLE_ADMIN")
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult result) {
        if (result.hasFieldErrors()) {
            return UtilCrud.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
    }

    @Operation(summary = "Actualizar una tarea existente por Id -> ROLE_ADMIN")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody Task task, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return UtilCrud.validation(result);
        }
        Optional<Task> optional = taskService.update(id, task);
        return optional.map(t -> ResponseEntity.status(HttpStatus.CREATED).body(t)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una tarea por Id -> ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Task> optional = taskService.delete(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}