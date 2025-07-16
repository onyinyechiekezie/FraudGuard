package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.dto.request.TermiiSmsRequest;
import com.fraudguard.fraudguard.dto.response.TermiiSmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TermiiSmsService implements SmsService {

    @Value("${termii.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String TERMII_SMS_URL = "https://api.ng.termii.com/api/sms/send";

    public TermiiSmsResponse sendSms(String to, String from, String messageBody, String channel) {
        TermiiSmsRequest request = new TermiiSmsRequest();
        request.setTo(to);
        request.setFrom(from);
        request.setSms(messageBody);
        request.setType("plain");
        request.setChannel(channel);
        request.setApi_key(apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TermiiSmsRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<TermiiSmsResponse> response = restTemplate
                .postForEntity(TERMII_SMS_URL, entity, TermiiSmsResponse.class);

        return response.getBody();
    }
}
