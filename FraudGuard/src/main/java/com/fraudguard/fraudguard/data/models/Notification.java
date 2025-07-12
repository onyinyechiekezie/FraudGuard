package com.fraudguard.fraudguard.data.models;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String userId;
    private String message;
    private AlertLevel alertLevel;
    private LocalDateTime timestamp;
    private boolean isRead;
}
