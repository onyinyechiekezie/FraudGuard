package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.data.models.Notification;
import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.NotificationRepository;
import com.fraudguard.fraudguard.data.repositories.UserRepository;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.exceptions.AccessDeniedException;
import com.fraudguard.fraudguard.exceptions.ResourceNotFoundException;
import com.fraudguard.fraudguard.exceptions.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public void createNotification(String userId, String message, AlertLevel alertLevel) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setAlertLevel(alertLevel);
        notification.setRead(false);
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    @Override
    public NotificationResponse viewNotification(String token, String notificationId) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("Session expired. Please log in."));

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found."));

        if (!notification.getUserId().equals(user.getId())) {
            throw new AccessDeniedException("You are not authorized to view this notification.");
        }

        // Mark as read
        if (!notification.isRead()) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }

        return new NotificationResponse(
                notification.getMessage(),
                notification.isRead(),
                notification.getAlertLevel(),
                notification.getTimestamp()
        );
    }

    @Override
    public List<com.fraudguard.fraudguard.dto.response.NotificationResponse> getUserNotifications(String token, int page, int size) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("We couldn’t verify your session. Please log in again."));

//        Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));

        List<Notification> pagedNotifications = notificationRepository
                .findByUserId(user.getId(), pageable);
//        did query sorting here
        return pagedNotifications.stream()
                .map(n -> new com.fraudguard.fraudguard.dto.response.NotificationResponse(
                        n.getMessage(),
                        n.isRead(),
                        n.getAlertLevel(),
                        n.getTimestamp()
                ))
                .toList();
    }


    @Override
    public List<NotificationResponse> viewDailyNotifications(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("Session expired. Please log in."));

        LocalDate today = LocalDate.now();

        List<Notification> dailyNotifications = notificationRepository
                .findByUserIdAndTimestampBetween(
                        user.getId(),
                        today.atStartOfDay(),
                        today.atTime(LocalTime.MAX)
                );

        return dailyNotifications.stream()
                .sorted(Comparator.comparing(Notification::getTimestamp).reversed())
                .map(n -> new NotificationResponse(
                        n.getMessage(),
                        n.isRead(),
                        n.getAlertLevel(),
                        n.getTimestamp()
                ))
                .toList();
    }


    @Override
    public void markAsRead(String token, String notificationId) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("Session invalid or expired"));

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        if (!notification.getUserId().equals(user.getId())) {
            throw new UnauthenticatedException("You are not authorized to modify this notification");
        }

        notification.setRead(true);
        notificationRepository.save(notification);
    }


}
