package com.fraudguard.fraudguard.dto.response;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class NotificationResponse {

    private String message;
    private boolean isRead;
    private AlertLevel alertLevel;
    private LocalDateTime timestamp;
}
