package com.fraudguard.fraudguard.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "transaction_logs")
public class TransactionLog {

    @Id
    private String id;
    private String userId;
    private boolean isFlagged;
    private LocalDateTime timestamp;
}
