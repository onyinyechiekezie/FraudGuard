package com.fraudguard.fraudguard.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "activity_logs")
public class ActivityLog {

    @Id
    private String id;
    private String userId;
    private String action; // e.g. LOGIN, LOGOUT, REPORT_VIEWED
    private LocalDateTime timestamp;


}
