package com.example.live_stream_demo.model.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterRequest {
    private String username;
    private String password;
}
