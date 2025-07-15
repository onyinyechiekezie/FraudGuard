package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

//    NotificationResponse viewNotification(String token, String notificationId);

    List<NotificationResponse> getUserNotifications(String token, int page, int size);

//    List<NotificationResponse> viewDailyNotifications(String token);

    void markAsRead(String token, String notificationId);

    void createNotification(String userId, String message, AlertLevel alertLevel);
}
