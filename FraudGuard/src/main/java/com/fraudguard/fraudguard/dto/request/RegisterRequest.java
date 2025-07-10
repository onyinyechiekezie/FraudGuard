package com.fraudguard.fraudguard.dto.request;

import com.fraudguard.fraudguard.data.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;
}
