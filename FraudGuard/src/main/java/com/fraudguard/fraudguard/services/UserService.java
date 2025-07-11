package com.fraudguard.fraudguard.services;


import com.fraudguard.fraudguard.dto.request.LoginRequest;
import com.fraudguard.fraudguard.dto.request.RegisterRequest;
import com.fraudguard.fraudguard.dto.response.LoginResponse;
import com.fraudguard.fraudguard.dto.response.LogoutResponse;
import com.fraudguard.fraudguard.dto.response.RegisterResponse;

public interface UserService {


    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    LogoutResponse logout(String token);
}