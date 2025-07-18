package com.fraudguard.fraudguard.dto.response;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AlertLogResponse {

    private String message;
    private String source;
    private AlertLevel alertLevel;
    private boolean isFake;
    private String userFeedback;
    private LocalDateTime timestamp;
}
