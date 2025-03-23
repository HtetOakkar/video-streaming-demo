package com.example.live_stream_demo.model.mapper.impl;

import com.example.live_stream_demo.model.mapper.RoleMapper;
import com.example.live_stream_demo.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;
}
