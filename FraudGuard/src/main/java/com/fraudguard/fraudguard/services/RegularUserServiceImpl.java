package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegularUserServiceImpl implements RegularUserService {

    private final UserRepository userRepository;

    @Override
    public RegularUser getRegularUserByToken(String token) {
        User user = userRepository.findBySessionToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or missing session token"));

        if (!(user instanceof RegularUser)) {
            throw new RuntimeException("Access denied: Only Regular Users can access this dashboard");
        }

        return (RegularUser) user;
    }
}
