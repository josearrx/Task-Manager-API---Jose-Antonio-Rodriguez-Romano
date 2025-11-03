package com.hitss.springboot.taskmanager.controllers;

import com.hitss.springboot.taskmanager.models.User;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login", description = "")
public class AuthController {
    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok("Este endpoint es manejado por Spring Security.");
    }
}