package kz.iitu.javaLabs.controller;

import kz.iitu.javaLabs.dto.ScheduleNotificationRequest;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.security.jwt.JwtTokenProvider;
import kz.iitu.javaLabs.service.NotificationService;
import kz.iitu.javaLabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public NotificationController(NotificationService notificationService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.notificationService = notificationService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleNotification(@RequestBody ScheduleNotificationRequest request, ServletRequest servletRequest) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        try {
            notificationService.scheduleMedicineNotifications(
                user.getId(),
                request.getMedicineName(),
                request.getScheduledTime()
            );
            return ResponseEntity.ok("Notification scheduled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduling notification");
        }
    }
}