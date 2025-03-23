package com.example.live_stream_demo.model.mapper.impl;

import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.entity.LiveStream;
import com.example.live_stream_demo.model.mapper.LiveStreamMapper;
import com.example.live_stream_demo.model.payload.response.LiveStreamCreateResponse;
import com.example.live_stream_demo.model.payload.response.LiveStreamViewResponse;
import org.springframework.stereotype.Component;

@Component
public class LiveStreamMapperImpl implements LiveStreamMapper {
    @Override
    public LiveStreamDto toDto(LiveStream liveStream) {
        if (liveStream == null) {
            return null;
        }

        return LiveStreamDto.builder()
                .id(liveStream.getId())
                .title(liveStream.getTitle())
                .description(liveStream.getDescription())
                .playbackUrl(liveStream.getPlaybackUrl())
                .createdAt(liveStream.getCreatedAt())
                .updatedAt(liveStream.getUpdateAt())
                .build();
    }

    @Override
    public LiveStreamCreateResponse toCreateResponse(LiveStreamDto liveStreamDto) {
        return LiveStreamCreateResponse.builder()
                .id(liveStreamDto.getId())
                .title(liveStreamDto.getTitle())
                .description(liveStreamDto.getDescription())
                .createdAt(liveStreamDto.getCreatedAt())
                .updatedAt(liveStreamDto.getUpdatedAt())
                .build();
    }

    @Override
    public LiveStreamViewResponse toViewResponse(LiveStreamDto liveStreamDto) {
        return LiveStreamViewResponse.builder()
                .id(liveStreamDto.getId())
                .title(liveStreamDto.getTitle())
                .description(liveStreamDto.getDescription())
                .playbackUrl(liveStreamDto.getPlaybackUrl())
                .createdAt(liveStreamDto.getCreatedAt())
                .updatedAT(liveStreamDto.getUpdatedAt())
                .build();
    }
}
