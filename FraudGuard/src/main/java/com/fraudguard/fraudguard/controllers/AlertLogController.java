package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.dto.response.AlertLogResponse;
import com.fraudguard.fraudguard.services.AlertLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertLogController {

    private final AlertLogService alertLogService;

    @GetMapping
    public ResponseEntity<List<AlertLogResponse>> getUserAlerts(
            @RequestHeader("Session-Token") String token
    ) {
        List<AlertLogResponse> alerts = alertLogService.getUserAlerts(token);
        return ResponseEntity.ok(alerts);
    }

    @PatchMapping("/{id}/mark-fake")
    public ResponseEntity<String> markAsFake(
            @PathVariable String id,
            @RequestParam(required = false) String feedback
    ) {
        alertLogService.markAlertAsFake(id, feedback);
        return ResponseEntity.ok("Alert marked as fake.");
    }


}
