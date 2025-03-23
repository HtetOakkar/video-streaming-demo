package com.example.live_stream_demo.model.dto;

import com.example.live_stream_demo.model.enums.RoleName;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;
    private RoleName name;
}
