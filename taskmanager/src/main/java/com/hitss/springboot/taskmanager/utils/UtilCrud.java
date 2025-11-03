package com.hitss.springboot.taskmanager.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class UtilCrud {
    public static ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            String field = error.getField();
            errors.put(field, "El campo '" + field + "' " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}