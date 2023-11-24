package kz.iitu.javaLabs.dto;

import java.time.LocalDateTime;

public class ScheduleNotificationRequest {

    private String medicineName;
    private LocalDateTime scheduledTime;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
