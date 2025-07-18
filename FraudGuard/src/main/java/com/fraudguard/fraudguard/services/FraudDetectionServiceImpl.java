package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.AlertLog;
import com.fraudguard.fraudguard.data.repositories.AlertLogRepository;
import com.fraudguard.fraudguard.dto.response.AlertValidationResponse;
import com.fraudguard.fraudguard.services.BankAlertValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final BankAlertValidatorService bankAlertValidatorService;
    private final AlertLogRepository alertLogRepository;
    private final TermiiSmsService termiiSmsService;

    @Override
    public void scanIncomingMessage(String userId, String sender, String message, String userPhone) {
        AlertValidationResponse response = bankAlertValidatorService.validateAlert(sender, message);


        boolean isFake = response.getAlertLevel().name().equalsIgnoreCase("FRAUD") ||
                response.getAlertLevel().name().equalsIgnoreCase("WARNING");

        AlertLog alert = AlertLog.builder()
                .userId(userId)
                .message(message)
                .source(sender)
                .alertLevel(response.getAlertLevel())
                .isFake(isFake)
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