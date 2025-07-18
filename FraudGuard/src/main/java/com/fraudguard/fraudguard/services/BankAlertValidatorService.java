package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.dto.response.AlertValidationResponse;

public interface BankAlertValidatorService {

    AlertValidationResponse validateAlert(String sender, String message);
}
