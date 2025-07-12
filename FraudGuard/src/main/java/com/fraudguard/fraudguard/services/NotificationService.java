package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getUserNotifications(String token, int page, int size);
    void markAsRead(String token, String notificationId);

    void createNotification(String userId, String message, AlertLevel alertLevel);
}
