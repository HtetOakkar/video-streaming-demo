package com.example.live_stream_demo.model.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;
    private List<RoleDto> roles;
}
