package com.example.live_stream_demo.controller;

import com.example.live_stream_demo.model.payload.request.AccountRegisterRequest;
import com.example.live_stream_demo.model.payload.request.LoginRequest;
import com.example.live_stream_demo.model.payload.response.ApiResponse;
import com.example.live_stream_demo.model.payload.response.TokenResponse;
import com.example.live_stream_demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users/login")
    public TokenResponse authenticateUser(@RequestBody LoginRequest request ) {

        return authService.authenticateUser(request);
    }

    @PostMapping("/admins/login")
    public TokenResponse authenticateAdmin(@RequestBody LoginRequest request) {
        return authService.authenticateAdmin(request);
    }

    @PostMapping("/users/sign-up")
    public ApiResponse registerUser(@RequestBody AccountRegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        authService.registerUser(request);
        return new ApiResponse("You have successfully registered. Please login again.", true);
    }



}
