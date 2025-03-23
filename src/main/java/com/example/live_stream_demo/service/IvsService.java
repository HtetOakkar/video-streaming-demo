package com.example.live_stream_demo.service;

import com.example.live_stream_demo.model.dto.IvsUrlDto;

public interface IvsService {
    IvsUrlDto createChannel(String channelName);

    String getLiveStreamUrls(String channelArn);
}
