package com.fraudguard.fraudguard.data.models;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "alert_logs")
@Builder
public class AlertLog {

    @Id
    private String id;

    private String userId;
    private String message;
    private String source;
    private AlertLevel alertLevel;
    private boolean isFake;
    private String userFeedback;
    private LocalDateTime createdAt;
}
