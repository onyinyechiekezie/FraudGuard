package com.fraudguard.fraudguard.mappers;

import com.fraudguard.fraudguard.data.enums.UserRole;
import com.fraudguard.fraudguard.dto.request.RegisterRequest;
import com.fraudguard.fraudguard.data.models.Agent;
import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.data.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.fraudguard.fraudguard.data.enums.UserRole.AGENT;

@Component
public class UserMapper {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromRegisterRequest(RegisterRequest request) {
        User user;

        if (request.getRole() == UserRole.AGENT) {
            user = new Agent();
        } else {
            user = new RegularUser();
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return user;

    }
}

