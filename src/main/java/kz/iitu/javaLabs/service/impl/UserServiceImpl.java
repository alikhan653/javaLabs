package kz.iitu.javaLabs.service.impl;

import kz.iitu.javaLabs.dto.AdminUserDto;
import kz.iitu.javaLabs.dto.UserDto;
import kz.iitu.javaLabs.model.Role;
import kz.iitu.javaLabs.model.Status;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.repository.RoleRepository;
import kz.iitu.javaLabs.repository.UserRepository;
import kz.iitu.javaLabs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }
    @Override
    public boolean exists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    @Override
    public void update(AdminUserDto adminUserDto) {
        User existingUser = userRepository.findById(adminUserDto.getId()).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(adminUserDto.getUsername());
            existingUser.setFirstName(adminUserDto.getFirstName());
            existingUser.setLastName(adminUserDto.getLastName());
            existingUser.setEmail(adminUserDto.getEmail());
            existingUser.setPassword(passwordEncoder.encode(adminUserDto.getPassword()));
            existingUser.setStatus(Status.valueOf(adminUserDto.getStatus()));
            User updatedUser = userRepository.save(existingUser);
            log.info("IN update - user: {} successfully registered", updatedUser);
        }
    }

    public void addRelative(Long patientId, User relative) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        relative.setPatient(patient);
        userRepository.save(relative);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
