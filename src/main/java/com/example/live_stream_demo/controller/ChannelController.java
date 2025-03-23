package com.example.live_stream_demo.controller;

import com.example.live_stream_demo.model.dto.ChannelDto;
import com.example.live_stream_demo.model.mapper.ChannelMapper;
import com.example.live_stream_demo.model.payload.request.ChannelCreationRequest;
import com.example.live_stream_demo.model.payload.response.ChannelResponse;
import com.example.live_stream_demo.security.CurrentUser;
import com.example.live_stream_demo.security.UserPrincipal;
import com.example.live_stream_demo.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/channels")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    private final ChannelMapper channelMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BROADCASTER')")
    public ResponseEntity<?> createChannel(@RequestBody ChannelCreationRequest request, @CurrentUser UserPrincipal userPrincipal) {

        ChannelDto channelDto = channelService.createChannel(request, userPrincipal.getId());

        ChannelResponse response = channelMapper.toResponse(channelDto);

        return ResponseEntity.created(URI.create("/api/channels/" + channelDto.getId())).body(response);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public ChannelResponse getChannel(@PathVariable Long id) {
        ChannelDto channelDto = channelService.getChannel(id);
        return channelMapper.toResponse(channelDto);
    }
}
