package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.AlertLog;
import com.fraudguard.fraudguard.data.repositories.AlertLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudDetectionServiceImpl implements FraudDetectionService{

    private final AiClient aiClient; // Injected Spring AI client
    private final AlertLogRepository alertLogRepository;

    public void scanIncomingMessage(String sender, String message) {
        String fullMessage = "Sender: " + sender + "\nMessage: " + message;

        AiResponse response = aiClient.call(fullMessage); // Spring AI analyzes it

        AlertLog alert = AlertLog.builder()
                .userId("system") // or get from context if tied to user
                .message(message)
                .source(sender)
                .alertLevel(response.getAlertLevel())
                .timestamp(LocalDateTime.now())
                .build();

        alertLogRepository.save(alert);

        // You could notify the user here too
    }
}
