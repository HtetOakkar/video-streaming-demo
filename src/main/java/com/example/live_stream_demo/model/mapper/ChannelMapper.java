package com.example.live_stream_demo.model.mapper;

import com.example.live_stream_demo.model.dto.ChannelDto;
import com.example.live_stream_demo.model.entity.Channel;
import com.example.live_stream_demo.model.payload.response.ChannelResponse;

public interface ChannelMapper {
    ChannelDto toDto(Channel channel);

    ChannelResponse toResponse(ChannelDto channelDto);
}
