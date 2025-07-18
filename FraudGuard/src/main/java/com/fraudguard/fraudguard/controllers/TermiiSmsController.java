package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.dto.response.AlertValidationResponse;
import com.fraudguard.fraudguard.dto.response.TermiiSmsResponse;
import com.fraudguard.fraudguard.services.BankAlertValidatorService;
import com.fraudguard.fraudguard.services.TermiiSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/termii")
@RequiredArgsConstructor
public class TermiiSmsController {

    private final TermiiSmsService smsService;
    private final BankAlertValidatorService bankAlertValidatorService;

    @PostMapping("/send")
    public ResponseEntity<AlertValidationResponse> sendSmsAndAnalyze(
            @RequestParam String to,
            @RequestParam String message,
            @RequestParam(defaultValue = "FraudGuard") String from,
            @RequestParam(defaultValue = "dnd") String channel
    ) {
        AlertValidationResponse response = bankAlertValidatorService.validateAlert(from, message);

        String smsContent = "🔎 Alert Level: " + response.getAlertLevel()
                + "\nSender: " + from
                + "\nMessage: " + message
                + "\nReason: " + response.getExplanation();

        smsService.sendSms(to, from, smsContent, channel);
        return ResponseEntity.ok(response);
    }

}
