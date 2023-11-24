package kz.iitu.javaLabs.service.impl;

import kz.iitu.javaLabs.model.Notification;
import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.repository.NotificationRepository;
import kz.iitu.javaLabs.repository.UserRepository;
import kz.iitu.javaLabs.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;


    private final UserRepository userRepository;

    public NotificationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void scheduleMedicineNotifications(Long userId, String medicineName, LocalDateTime scheduledTime) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMedicineName(medicineName);
        notification.setScheduledTime(scheduledTime);

        notificationRepository.save(notification);

        sendNotification(notification);
    }

    private void sendNotification(Notification notification) {
        // Implement notification sending logic (e.g., push notification, email)
    }
}