package com.hitss.springboot.taskmanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        String method = request.getMethod();
        String path = request.getServletPath();
        
        String requiredRole;
        String message;

        if (path.startsWith("/tasks") && (method.equals("PUT") || method.equals("DELETE"))) {
            requiredRole = "ROLE_ADMIN";
            message = String.format("Acceso denegado. Solo los usuarios con el rol %s tienen permiso para ejecutar la acción '%s' en tareas.", requiredRole, method.toLowerCase());
        } else if (path.startsWith("/api/users") && method.equals("GET")) {
             requiredRole = "ROLE_ADMIN"; 
             message = String.format("Acceso Denegado. No tiene los permisos (roles) necesarios.");
        } else {
            requiredRole = "Roles no especificados";
            message = "Acceso Denegado. No tiene los permisos (roles) necesarios.";
        }
        
        Map<String, String> body = new HashMap<>();
        body.put("error", accessDeniedException.getMessage());
        body.put("message", message);
        body.put("path", path);
        
        response.setContentType(TokenJwtConfig.CONTENT_TYPE);
        response.setStatus(HttpStatus.FORBIDDEN.value()); // 403
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    }
}