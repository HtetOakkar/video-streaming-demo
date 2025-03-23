package com.example.live_stream_demo.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ChannelResponse {
    private Long id;
    private String name;
    @JsonProperty("ingest_url")
    private String ingestUrl;
    @JsonProperty("stream_key")
    private String streamKey;
    @JsonProperty("created_at")
    private Instant createdAt;
}
