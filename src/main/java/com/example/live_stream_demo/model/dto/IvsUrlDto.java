package com.example.live_stream_demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IvsUrlDto {
    private String ingestUrl;
    private String channelArn;
    private String streamKey;
}
