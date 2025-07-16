package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.dto.response.AlertValidationResponse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAlertValidatorServiceImpl implements BankAlertValidatorService {

    private static final List<String> trustedSenders = List.of("UBA", "GTBank", "AccessBank");
    private static final Pattern refIdPattern = Pattern.compile("Ref\\s*[:=]\\s*([A-Z0-9]{8,})");

    @Override
    public AlertValidationResponse validateAlert(String sender, String message) {
        if (!trustedSenders.contains(sender.trim())) {
            return new AlertValidationResponse(true, AlertLevel.FRAUD, "Unrecognized sender");
        }
        Matcher matcher = refIdPattern.matcher(message);
        if (!matcher.find()) {
            return new AlertValidationResponse(true, AlertLevel.WARNING, "Missing or invalid reference ID");
        }

        return new AlertValidationResponse(false, AlertLevel.SAFE, "Message appears valid");
    }

}
