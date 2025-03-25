package com.example.live_stream_demo.service;

import com.example.live_stream_demo.model.dto.ChannelDto;
import com.example.live_stream_demo.model.payload.request.ChannelCreationRequest;

public interface ChannelService {
    ChannelDto createChannel(ChannelCreationRequest request, Long id);

    ChannelDto getChannel(Long id);

    String getChannelPlaybackUrl(Long channelId);
}
