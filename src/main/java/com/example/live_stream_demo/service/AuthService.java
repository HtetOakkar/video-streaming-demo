package com.example.live_stream_demo.service;


import com.example.live_stream_demo.model.payload.request.AccountRegisterRequest;
import com.example.live_stream_demo.model.payload.request.LoginRequest;
import com.example.live_stream_demo.model.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse authenticateUser(LoginRequest request);

    TokenResponse authenticateAdmin(LoginRequest request);

    void registerUser(AccountRegisterRequest request);
}
