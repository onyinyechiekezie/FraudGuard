package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.services.FraudDetectionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fraud")
public class FraudDetectionController {

    private final FraudDetectionServiceImpl fraudDetectionService;

    @PostMapping("/scan")
    public ResponseEntity<String> scan(
            @RequestParam String userId,
            @RequestParam String sender,
            @RequestParam String message,
            @RequestParam String phone
    ) {
        fraudDetectionService.scanIncomingMessage(userId, sender, message, phone);
        return ResponseEntity.ok("Message scanned and alert sent.");
    }
}
