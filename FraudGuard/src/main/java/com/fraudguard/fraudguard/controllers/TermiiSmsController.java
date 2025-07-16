package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.dto.response.TermiiSmsResponse;
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

    @PostMapping("/send")
    public ResponseEntity<TermiiSmsResponse> sendSms(
            @RequestParam String to,
            @RequestParam String message,
            @RequestParam(defaultValue = "talert") String from,
            @RequestParam(defaultValue = "generic") String channel
    ) {
        TermiiSmsResponse response = smsService.sendSms(to, from, message, channel);
        return ResponseEntity.ok(response);
    }
}
