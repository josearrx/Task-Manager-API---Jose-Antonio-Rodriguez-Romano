package com.hitss.springboot.taskmanager.repositories;

import com.hitss.springboot.taskmanager.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}