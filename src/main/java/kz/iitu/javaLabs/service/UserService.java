package kz.iitu.javaLabs.service;


import kz.iitu.javaLabs.dto.AdminUserDto;
import kz.iitu.javaLabs.dto.UserDto;
import kz.iitu.javaLabs.model.User;

import java.util.List;


public interface UserService {
    boolean exists(Long userId);
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    void update(AdminUserDto existingUser);

    void save(User user);

    void addRelative(Long patientId, User relative);
}