package com.example.live_stream_demo.service;


import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.payload.request.LiveStreamRequest;

public interface LiveStreamService {
    LiveStreamDto createLiveStream(Long channelId, Long userId, LiveStreamRequest request);

    LiveStreamDto getLiveStream(Long id);
}
