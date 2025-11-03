package com.hitss.springboot.taskmanager.models;

import com.hitss.springboot.taskmanager.validation.ExistsByUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @ExistsByUsername
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private List<Role> roles;

    private boolean enabled;

    @Transient
    private boolean admin;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }
}