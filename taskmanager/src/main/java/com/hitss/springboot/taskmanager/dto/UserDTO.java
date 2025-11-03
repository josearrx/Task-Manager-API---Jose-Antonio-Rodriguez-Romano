package com.hitss.springboot.taskmanager.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    @NotBlank(
        message = "El nombre de usuario es obligatorio"
    )
    @Size(min = 3, max = 50 , message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    private String username;

   @NotBlank(
        message = "La contraseña es obligatoria"
    )
    private String password;

    private Set<String> roles; // USER, ADMIN

}
