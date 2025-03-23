package com.example.live_stream_demo.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelCreationRequest {
    @JsonProperty("channel_name")
    private String channelName;
}
