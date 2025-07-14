package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getNotifications(
            @RequestHeader("Session-Token") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<NotificationResponse> notifications = notificationService.getUserNotifications(token, page, size);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> viewNotification(
            @RequestHeader("Session-Token") String token,
            @PathVariable String id
    ) {
        NotificationResponse response = notificationService.viewNotification(token, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<String> markNotificationAsRead(
            @RequestHeader("Session-Token") String token,
            @PathVariable String id
    ) {
        notificationService.markAsRead(token, id);
        return ResponseEntity.ok("Notification marked as read.");
    }

}
