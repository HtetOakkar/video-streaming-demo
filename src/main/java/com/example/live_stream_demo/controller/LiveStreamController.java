package com.example.live_stream_demo.controller;

import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.mapper.LiveStreamMapper;
import com.example.live_stream_demo.model.payload.request.LiveStreamRequest;
import com.example.live_stream_demo.model.payload.response.LiveStreamCreateResponse;
import com.example.live_stream_demo.model.payload.response.LiveStreamViewResponse;
import com.example.live_stream_demo.security.CurrentUser;
import com.example.live_stream_demo.security.UserPrincipal;
import com.example.live_stream_demo.service.LiveStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LiveStreamController {

    private final LiveStreamMapper liveStreamMapper;

    private final LiveStreamService liveStreamService;

    @PostMapping("/channels/{channelId}/live-streams")
    @PreAuthorize("hasRole('ROLE_BROADCASTER')")
    public ResponseEntity<?> createLiveStream(@PathVariable Long channelId, @CurrentUser UserPrincipal userPrincipal, @RequestBody LiveStreamRequest request) {
        LiveStreamDto liveStreamDto = liveStreamService.createLiveStream(channelId, userPrincipal.getId(), request);
        LiveStreamCreateResponse response = liveStreamMapper.toCreateResponse(liveStreamDto);
        return ResponseEntity.created(URI.create("/api/live-streams/" + liveStreamDto.getId())).body(response);
    }

    @GetMapping("/live-streams/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public LiveStreamViewResponse getLiveSteam(@PathVariable Long id) {
        LiveStreamDto liveStreamDto = liveStreamService.getLiveStream(id);
        return liveStreamMapper.toViewResponse(liveStreamDto);
    }


}
