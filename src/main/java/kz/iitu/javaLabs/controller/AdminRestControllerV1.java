package kz.iitu.javaLabs.controller;

import kz.iitu.javaLabs.dto.AdminUserDto;
import kz.iitu.javaLabs.dto.UserDto;
import kz.iitu.javaLabs.model.Role;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.security.jwt.JwtTokenProvider;
import kz.iitu.javaLabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@RequestBody AdminUserDto adminUserDto) {
        try {
            User newUser = adminUserDto.toUser();

            if (userService.exists(newUser.getId())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            newUser.setCreated(new Date());
            newUser.setUpdated(new Date());

            User createdUser = userService.register(newUser);
            UserDto responseDto = UserDto.fromUser(createdUser);

            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> updateUser(@PathVariable(name = "id") Long id,
                                                   @RequestBody AdminUserDto adminUserDto) {
        try {
            adminUserDto.setId(id);
            userService.update(adminUserDto);

            AdminUserDto updatedUserDto = AdminUserDto.fromUser(userService.findById(id));
            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        User userToDelete = userService.findById(id);

        if (userToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        try {
            User user = userService.findByUsername(principal.getName());

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("{patientId}/addRelative/{relativeId}")
    public ResponseEntity<String> addRelativeToPatient(
            @PathVariable Long patientId,
            @PathVariable Long relativeId) {

        User patient = userService.findById(patientId);
        User relative = userService.findById(relativeId);

        if (patient == null || relative == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient or relative not found.");
        }

        relative.setPatient(patient);

        userService.save(relative);

        return ResponseEntity.ok("Relative added to the patient successfully.");
    }
}
