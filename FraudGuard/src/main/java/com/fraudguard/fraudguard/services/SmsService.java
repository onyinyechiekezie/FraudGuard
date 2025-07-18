package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.dto.response.TermiiSmsResponse;

public interface SmsService {

    TermiiSmsResponse sendSms(String to, String from, String messageBody, String channel);
}
