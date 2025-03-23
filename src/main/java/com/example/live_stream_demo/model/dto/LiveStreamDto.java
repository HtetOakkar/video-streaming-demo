package com.example.live_stream_demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class LiveStreamDto {
    private Long id;
    private String title;
    private String description;
    private String playbackUrl;
    private Instant createdAt;
    private Instant updatedAt;
}
