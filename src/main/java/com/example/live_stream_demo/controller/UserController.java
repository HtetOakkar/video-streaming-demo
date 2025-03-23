package com.example.live_stream_demo.controller;

import com.example.live_stream_demo.exception.BadRequestException;
import com.example.live_stream_demo.model.mapper.UserMapper;
import com.example.live_stream_demo.model.payload.response.ApiResponse;
import com.example.live_stream_demo.security.CurrentUser;
import com.example.live_stream_demo.security.UserPrincipal;
import com.example.live_stream_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/broadcasters")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse upgradeToBroadcasterRole(@CurrentUser UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        if (id == null) {
            throw new BadRequestException("user id is null");
        }
        userService.upgradeToBroadcaster(id);

        return new ApiResponse("Successfully upgrade to broadcaster.", true);
    }
}
