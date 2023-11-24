package kz.iitu.javaLabs.repository;

import kz.iitu.javaLabs.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}