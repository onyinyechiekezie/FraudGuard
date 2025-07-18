package com.fraudguard.fraudguard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TermiiSmsResponse {

    private String message_id;
    private String message;
    private int balance;
    private String user;
}
