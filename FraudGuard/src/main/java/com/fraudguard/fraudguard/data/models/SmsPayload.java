package com.fraudguard.fraudguard.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class SmsPayload {

    @Id
    private String id;
    private String sender;
    private String message;
}
