package kz.iitu.javaLabs.controller;

import kz.iitu.javaLabs.dto.AdminUserDto;
import kz.iitu.javaLabs.dto.UserDto;
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


@RestController
@RequestMapping(value = "/api/v1/rel/")
public class RelativeRestControllerV1 {
    private final UserService userService;

    @Autowired
    public RelativeRestControllerV1(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "patient")
    public ResponseEntity<UserDto> getUserById(Principal principal){
        User user = userService.findByUsername(principal.getName());
        User patient = userService.findById(user.getPatient().getId());
        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(patient);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
