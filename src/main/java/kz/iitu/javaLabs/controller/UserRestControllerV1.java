package kz.iitu.javaLabs.controller;

import kz.iitu.javaLabs.dto.UserDto;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.model.UserLocation;
import kz.iitu.javaLabs.security.jwt.JwtTokenProvider;
import kz.iitu.javaLabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;


    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/{userId}/track")
//    public ResponseEntity<String> trackUserLocation(Principal principal, @RequestBody UserLocation userLocation) {
//
//        User user = userService.findByUsername(principal.getName());
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        userLocation.setUser(user);
//        userLocationService.saveUserLocation(userLocation);
//
//        return ResponseEntity.ok("User location saved successfully");
//    }
//
//    @GetMapping("/{userId}/location-history")
//    public ResponseEntity<List<UserLocation>> getUserLocationHistory(@PathVariable Long userId) {
//        List<UserLocation> userLocationHistory = userLocationService.getUserLocationsByUserId(userId);
//        return ResponseEntity.ok(userLocationHistory);
//    }

}
