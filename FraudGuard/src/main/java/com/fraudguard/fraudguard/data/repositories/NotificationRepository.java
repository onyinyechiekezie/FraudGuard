package com.fraudguard.fraudguard.data.repositories;

import com.fraudguard.fraudguard.data.models.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByUserId(String userId);
    List<Notification> findByUserId(String userId, Pageable pageable);

    @Query("{ 'userId': ?0, 'read': false }")
    List<Notification> findUnreadByUser(String userId);


    List<Notification> findByUserIdAndTimestampBetween(String id, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
