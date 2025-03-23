package com.example.live_stream_demo.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Date expiration;
}
