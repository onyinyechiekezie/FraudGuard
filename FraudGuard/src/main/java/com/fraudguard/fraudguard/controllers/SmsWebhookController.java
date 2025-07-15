package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.data.models.SmsPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsWebhookController {

    private final FraudDetectionService fraudDetectionService;

    @PostMapping("/receive")
    public ResponseEntity<String> receiveSms(@RequestBody SmsPayload smsPayload) {
        fraudDetectionService.scanIncomingMessage(smsPayload.getSender(), smsPayload.getMessage());
        return ResponseEntity.ok("SMS received and being analyzed");
    }
}
