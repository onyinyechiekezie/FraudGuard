package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.AlertLog;
import com.fraudguard.fraudguard.data.repositories.AlertLogRepository;
import com.fraudguard.fraudguard.dto.response.AiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final AiClientService aiClientService;
    private final AlertLogRepository alertLogRepository;
    private final TermiiSmsService termiiSmsService;

    @Override
    public void scanIncomingMessage(String userId, String sender, String message, String userPhone) {
        String fullMessage = "Sender: " + sender + "\nMessage: " + message;

        AiResponse response = aiClientService.call(fullMessage);

        AlertLog alert = AlertLog.builder()
                .userId(userId)
                .message(message)
                .source(sender)
                .alertLevel(response.getAlertLevel())
                .timestamp(LocalDateTime.now())
                .build();

        alertLogRepository.save(alert);
        String alertMessage = "🔎 Alert Level: " + response.getAlertLevel().name()
                + "\nSender: " + sender
                + "\nMessage: " + message
                + "\nExplanation: " + response.getExplanation();

        termiiSmsService.sendSms(userPhone, "FraudGuard", alertMessage, "dnd");
    }
}