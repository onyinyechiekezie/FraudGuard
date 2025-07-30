package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.data.models.AlertLog;
import com.fraudguard.fraudguard.data.models.Notification;
import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.*;
import com.fraudguard.fraudguard.dto.response.DailySummaryResponse;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;
import com.fraudguard.fraudguard.exceptions.AccessDeniedException;
import com.fraudguard.fraudguard.exceptions.ResourceNotFoundException;
import com.fraudguard.fraudguard.exceptions.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegularUserServiceImpl implements RegularUserService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final ActivityLogRepository activityLogRepository;
    private final AlertLogRepository alertLogRepository;

    @Override
    public RegularUser getRegularUserByToken(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or missing session token"));

        if (!(user instanceof RegularUser)) {
            throw new RuntimeException("Access denied: Only Regular Users can access this dashboard");
        }

        return (RegularUser) user;
    }

    @Override
    public RegularUserDashboardResponse getDashboardData(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("We couldn’t verify your session." +
                        " Please log in again to access your dashboard."));

        if (!(user instanceof RegularUser)) {
            throw new AccessDeniedException("You are not authorized to access this resource.");
        }
        LocalDate today = LocalDate.now();

        // Fetch notifications
        List<String> notifications = notificationRepository.findByUserId(user.getId())
                .stream()
                .sorted(Comparator.comparing(Notification::getTimestamp).reversed())
//                .limit(5)
                .map(Notification::getMessage)
                .toList();

        // Count today’s flagged transactions
        //LocalDate today = LocalDate.now();
        int flagged = transactionLogRepository.countByUserIdAndIsFlaggedAndTimestampBetween(
                user.getId(), true,
                today.atStartOfDay(),
                today.atTime(LocalTime.MAX)
        );

        // Login summary
        long loginCountToday = activityLogRepository.countByUserIdAndActionAndTimestampBetween(
                user.getId(), "LOGIN",
                today.atStartOfDay(),
                today.atTime(LocalTime.MAX)
        );

        return new RegularUserDashboardResponse(
                "Welcome back, " + user.getFirstName(),
                notifications,
                "You logged in " + loginCountToday + " times today.",
                flagged
        );
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
    public DailySummaryResponse viewDailySummary(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("Session expired. Please log in."));

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        List<AlertLog> todaysAlerts = alertLogRepository.findByUserIdAndTimestampBetween(
                user.getId(), startOfDay, endOfDay
        );

        int fraudAlerts = (int) todaysAlerts.stream()
                .filter(alert -> alert.getAlertLevel() == AlertLevel.FRAUD)
                .count();

        int safeAlerts = (int) todaysAlerts.stream()
                .filter(alert -> alert.getAlertLevel() == AlertLevel.SAFE)
                .count();

        int totalAlerts = todaysAlerts.size();

        return new DailySummaryResponse(totalAlerts, fraudAlerts, safeAlerts, today.toString());
    }


}
