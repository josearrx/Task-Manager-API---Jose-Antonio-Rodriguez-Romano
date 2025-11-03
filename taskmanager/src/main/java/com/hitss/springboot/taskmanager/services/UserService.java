package com.hitss.springboot.taskmanager.services;

import com.hitss.springboot.taskmanager.models.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}