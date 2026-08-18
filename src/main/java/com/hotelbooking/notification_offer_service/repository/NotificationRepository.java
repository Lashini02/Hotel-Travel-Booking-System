package com.hotelbooking.notification_offer_service.repository;

import com.hotelbooking.notification_offer_service.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserId(String userId);
}