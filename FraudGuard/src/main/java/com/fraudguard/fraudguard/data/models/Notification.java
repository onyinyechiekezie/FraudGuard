package com.fraudguard.fraudguard.data.models;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
