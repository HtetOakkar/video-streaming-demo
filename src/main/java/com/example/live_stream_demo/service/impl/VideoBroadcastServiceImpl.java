package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.service.VideoBroadCastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ivs.IvsClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoBroadcastServiceImpl implements VideoBroadCastService {

    private final IvsClient ivsClient;

    
}
