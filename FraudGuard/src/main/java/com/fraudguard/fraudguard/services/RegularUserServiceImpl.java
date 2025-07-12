//package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.Notification;
import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.NotificationRepository;
import com.fraudguard.fraudguard.data.repositories.UserRepository;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;
import com.fraudguard.fraudguard.exceptions.AccessDeniedException;
import com.fraudguard.fraudguard.exceptions.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class RegularUserServiceImpl implements RegularUserService {
//
//    private final UserRepository userRepository;
//    private final NotificationRepository notificationRepository;
//
//    @Override
//    public RegularUser getRegularUserByToken(String token) {
//        User user = userRepository.findBySessionToken(token)
//                .orElseThrow(() -> new RuntimeException("Invalid or missing session token"));
//
//        if (!(user instanceof RegularUser)) {
//            throw new RuntimeException("Access denied: Only Regular Users can access this dashboard");
//        }
//
//        return (RegularUser) user;
//    }
//
//    @Override
//    public RegularUserDashboardResponse getDashboardData(String token) {
//        User user = userRepository.findBySessionToken(token)
//                .orElseThrow(() -> new UnauthenticatedException("We couldn’t verify your session." +
//                        " Please log in again to access your dashboard."));
//
//        if (!(user instanceof RegularUser)) {
//            throw new AccessDeniedException("You are not authorized to access this resource.");
//        }
//
//        // Fetch notifications
//        List<String> notifications = notificationRepository.findByUserId(user.getId())
//                .stream()
//                .sorted(Comparator.comparing(Notification::getTimestamp).reversed())
////                .limit(5)
//                .map(Notification::getMessage)
//                .toList();

//        // Count today’s flagged transactions
//        LocalDate today = LocalDate.now();
//        int flagged = transactionLogRepository.countByUserIdAndIsFlaggedAndTimestampBetween(
//                user.getId(), true,
//                today.atStartOfDay(),
//                today.atTime(LocalTime.MAX)
//        );
//
//        // Login summary
//        long loginCountToday = activityLogRepository.countByUserIdAndActionAndTimestampBetween(
//                user.getId(), "LOGIN",
//                today.atStartOfDay(),
//                today.atTime(LocalTime.MAX)
//        );
//
//        return new RegularUserDashboardResponse(
//                "Welcome back, " + user.getFirstname(),
//                notifications,
//                "You logged in " + loginCountToday + " times today.",
//                flagged
//        );
//    }
//}
