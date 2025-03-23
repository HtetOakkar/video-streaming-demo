package com.example.live_stream_demo.model.mapper.impl;

import com.example.live_stream_demo.model.dto.ChannelDto;
import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.entity.Channel;
import com.example.live_stream_demo.model.mapper.ChannelMapper;
import com.example.live_stream_demo.model.mapper.LiveStreamMapper;
import com.example.live_stream_demo.model.payload.response.ChannelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChannelMapperImpl implements ChannelMapper {

    private final LiveStreamMapper liveStreamMapper;

    @Override
    public ChannelDto toDto(Channel channel) {
        if (channel == null) {
            return null;
        }

        List<LiveStreamDto> liveStreamDtos = new ArrayList<>();
        if (channel.getLiveStreams() != null) {
            liveStreamDtos = channel.getLiveStreams().stream().map(liveStreamMapper::toDto).toList();
        }

        return ChannelDto.builder()
                .id(channel.getId())
                .name(channel.getName())
                .arn(channel.getArn())
                .ingestUrl(channel.getIngestUrl())
                .streamKey(channel.getStreamKey())
                .createdAt(channel.getCreatedAt())
                .updatedAt(channel.getUpdatedAt())
                .liveStreams(liveStreamDtos)
                .build();
    }

    @Override
    public ChannelResponse toResponse(ChannelDto channelDto) {

        return ChannelResponse.builder()
                .id(channelDto.getId())
                .name(channelDto.getName())
                .ingestUrl(channelDto.getIngestUrl())
                .streamKey(channelDto.getStreamKey())
                .createdAt(channelDto.getCreatedAt())
                .build();
    }
}
