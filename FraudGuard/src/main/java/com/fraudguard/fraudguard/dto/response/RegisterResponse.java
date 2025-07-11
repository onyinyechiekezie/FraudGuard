package com.fraudguard.fraudguard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private String userId;
    private String role;
}
