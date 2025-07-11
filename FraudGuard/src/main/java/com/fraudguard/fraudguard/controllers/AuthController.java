package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.data.models.User;
import com.fraudguard.fraudguard.dto.request.LoginRequest;
import com.fraudguard.fraudguard.dto.request.RegisterRequest;
import com.fraudguard.fraudguard.dto.response.LoginResponse;
import com.fraudguard.fraudguard.dto.response.RegisterResponse;
import com.fraudguard.fraudguard.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
//
//    @GetMapping("/profile")
//    public ResponseEntity<UserProfileResponse> getProfile(@RequestHeader("X-Auth-Token") String token) {
//        User user = userService.getUserByToken(token); // throws error if token is invalid
//        return ResponseEntity.ok(new UserProfileResponse(user.getFirstname(), user.getEmail()));
//    }

}
