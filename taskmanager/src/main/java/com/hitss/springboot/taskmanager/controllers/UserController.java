package com.hitss.springboot.taskmanager.controllers;

import com.hitss.springboot.taskmanager.models.User;
import com.hitss.springboot.taskmanager.services.UserService;
import com.hitss.springboot.taskmanager.utils.UtilCrud;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/users"})
@Tag(name = "Usuarios", description = "")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public List<User> list() {
        return this.userService.findAll();
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping({"/register"})
    public ResponseEntity<?> register(@RequestBody @Valid User user, BindingResult result) {
        user.setAdmin(false);
        return this.create(user, result);
    }

    // @Operation(summary = "Crear un nuevo usuario")
    // @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid User user, BindingResult result) {
        return result.hasFieldErrors() ? UtilCrud.validation(result) : ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }
}