package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.dto.response.AlertLogResponse;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AlertLogService {

    List<AlertLogResponse> getUserAlerts(String token);

    void markAlertAsFake(String alertId, @Nullable String feedback);
}
