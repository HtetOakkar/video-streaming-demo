package com.example.live_stream_demo.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class LiveStreamViewResponse {
    private Long id;
    private String title;
    private String description;
    @JsonProperty("playback_url")
    private String playbackUrl;
    @JsonProperty("created_at")
    private Instant createdAt;
    @JsonProperty("updated_at")
    private Instant updatedAT;
}
