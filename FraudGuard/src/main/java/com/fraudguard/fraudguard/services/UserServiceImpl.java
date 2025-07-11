package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.data.repositories.UserRepository;
import com.fraudguard.fraudguard.dto.request.LoginRequest;
import com.fraudguard.fraudguard.dto.request.RegisterRequest;
import com.fraudguard.fraudguard.dto.response.LoginResponse;
import com.fraudguard.fraudguard.dto.response.LogoutResponse;
import com.fraudguard.fraudguard.dto.response.RegisterResponse;
import com.fraudguard.fraudguard.exceptions.EmailAlreadyExistsException;
import com.fraudguard.fraudguard.exceptions.LoginFailedException;
import com.fraudguard.fraudguard.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already in use");
        }

        User user = userMapper.fromRegisterRequest(request);
        user = userRepository.save(user);

        return new RegisterResponse(
                "User registered successfully",
                user.getId(),
                user.getRole().name()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginFailedException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new LoginFailedException("Invalid email or password");
        }

        String sessionToken = UUID.randomUUID().toString();
        user.setSessionToken(sessionToken);
        userRepository.save(user);

        return new LoginResponse(
                "Login successful",
                user.getId(),
                user.getRole().name(),
                sessionToken
        );
    }

    @Override
    public LogoutResponse logout(String token) {
        Optional<User> userOpt = userRepository.findBySessionToken(token);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid session token or already logged out");
        }

        User user = userOpt.get();
        user.setSessionToken(null);
        userRepository.save(user);

        return new LogoutResponse("User logged out successfully");
    }



}
