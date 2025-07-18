package com.fraudguard.fraudguard.data.models;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class FraudDetection {
    @Id
    private String id;

    private String userId;
    private String source;
    private String originalContent;
    private boolean isFraud;
    private String detectionReason;
    private AlertLevel alertLevel;
    private LocalDateTime timestamp;
}
