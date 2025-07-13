package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.services.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    // 🔔 View Single Notification
    @GetMapping("/{agentId}/notification")
    public ResponseEntity<NotificationResponse> viewNotification(@PathVariable String agentId) {
        NotificationResponse response = agentService.viewNotification(agentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{agentId}/daily-notification")
    public ResponseEntity<NotificationResponse> viewDailySummary(@PathVariable String agentId) {
        NotificationResponse dailySummary = agentService.viewDailyNotification(agentId);
        return ResponseEntity.ok(dailySummary);
    }


    @GetMapping("/{agentId}/notifications")
    public ResponseEntity<List<NotificationResponse>> getAllNotifications(@PathVariable String agentId) {
        List<NotificationResponse> notifications = agentService.getAgentNotifications(agentId);
        return ResponseEntity.ok(notifications);
    }
//
//

//    // 📦 View All Notifications for Agent
//    @GetMapping("/{agentId}/notifications")
//    public ResponseEntity<List<NotificationResponse>> getAllNotifications(@PathVariable String agentId) {
//        List<NotificationResponse> responses = agentService.getAgentNotifications(agentId);
//        return ResponseEntity.ok(responses);
//    }
}
