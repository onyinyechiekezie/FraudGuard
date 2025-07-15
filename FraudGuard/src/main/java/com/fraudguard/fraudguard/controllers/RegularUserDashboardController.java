package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.dto.response.DailySummaryResponse;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;
import com.fraudguard.fraudguard.services.RegularUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regular")
@RequiredArgsConstructor
public class RegularUserDashboardController {

    private final RegularUserService regularUserService;

    @GetMapping
    public ResponseEntity<RegularUserDashboardResponse> getDashboard(
            @RequestHeader("Session-Token") String token
    ) {
        RegularUserDashboardResponse response = regularUserService.getDashboardData(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> viewNotification(
            @RequestHeader("Session-Token") String token,
            @PathVariable String id
    ) {
        NotificationResponse response = regularUserService.viewNotification(token, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/today")
    public ResponseEntity<List<NotificationResponse>> getTodayNotifications(
            @RequestHeader("Session-Token") String token
    ) {
        List<NotificationResponse> notifications = regularUserService.viewDailyNotifications(token);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/summary/daily")
    public DailySummaryResponse viewDailySummary(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Missing Authorization token");
        }

        return regularUserService.viewDailySummary(token);
    }


}
