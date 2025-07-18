package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.AlertLog;
import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.AlertLogRepository;
import com.fraudguard.fraudguard.data.repositories.UserRepository;
import com.fraudguard.fraudguard.dto.response.AlertLogResponse;
import com.fraudguard.fraudguard.exceptions.ResourceNotFoundException;
import com.fraudguard.fraudguard.exceptions.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertLogServiceImpl implements AlertLogService {

    private final UserRepository userRepository;
    private final AlertLogRepository alertLogRepository;

    @Override
    public List<AlertLogResponse> getUserAlerts(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new UnauthenticatedException("Session expired. Please log in."));

        return alertLogRepository.findByUserId(user.getId())
                .stream()
                .sorted(Comparator.comparing(AlertLog::getTimestamp).reversed())
                .map(alert -> new AlertLogResponse(
                        alert.getMessage(),
                        alert.getSource(),
                        alert.getAlertLevel(),
                        alert.isFake(),
                        alert.getUserFeedback(),
                        alert.getTimestamp()
                ))
                .toList();
    }

    @Override
    public void markAlertAsFake(String alertId, String feedback) {
        AlertLog alert = alertLogRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));

        alert.setFake(true);

        alert.setUserFeedback(
                feedback != null && !feedback.isBlank() ? feedback : "No feedback provided"
        );


        alertLogRepository.save(alert);
    }


}
