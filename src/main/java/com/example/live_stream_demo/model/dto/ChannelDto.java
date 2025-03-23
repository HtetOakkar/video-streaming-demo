package com.example.live_stream_demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class ChannelDto {
    private Long id;
    private String name;
    private String arn;
    private String ingestUrl;
    private String streamKey;
    private Instant createdAt;
    private Instant updatedAt;
    private List<LiveStreamDto> liveStreams;
}
