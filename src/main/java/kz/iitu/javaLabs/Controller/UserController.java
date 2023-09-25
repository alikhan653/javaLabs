package kz.iitu.javaLabs.Controller;

import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findById/{id}")
    public User getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            user = new User(999L, null, null, null,null,null, null);
        }

        return user;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {return userService.getAll();}

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id){
        userService.delete(id);

    }

    @PostMapping("/saveUser")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}

