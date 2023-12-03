package kz.iitu.javaLabs.controller;

import kz.iitu.javaLabs.dto.LocationRequest;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.security.jwt.JwtTokenProvider;
import kz.iitu.javaLabs.service.LocationService;
import kz.iitu.javaLabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "http://localhost/test.html")
public class LocationController {
    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public LocationController(UserService userService, LocationService locationService) {
        this.locationService = locationService;
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<String> updateLocation(@RequestBody LocationRequest locationRequest, Principal principal) {
        System.out.println("Received request body: " + locationRequest);
        User user = new User();
        if (principal == null) {
            user = userService.findByUsername("admin");
        }else {
            user = userService.findByUsername(principal.getName());
        }
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();


        if (user != null) {
            locationService.trackLocation(user, latitude, longitude);
            return ResponseEntity.ok("Location updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated2");
        }
    }


}