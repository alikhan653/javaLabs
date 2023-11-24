package kz.iitu.javaLabs.service;

import java.time.LocalDateTime;

public interface NotificationService {
    void scheduleMedicineNotifications(Long patientId, String medicineName, LocalDateTime scheduledTime);
}