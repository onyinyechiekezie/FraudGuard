package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.services.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class IncomingMessageController {

    private final FraudDetectionService fraudDetectionService;

    @PostMapping("/receive")
    public ResponseEntity<String> receiveFakeMessage(
            @RequestParam String userId,
            @RequestParam String sender,
            @RequestParam String message,
            @RequestParam String phone
    ) {
        fraudDetectionService.scanIncomingMessage(userId, sender, message, phone);
        return ResponseEntity.ok("Message received and Processing.");
    }
}
