package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.exception.RecordNotFoundException;
import com.example.live_stream_demo.model.dto.IvsUrlDto;
import com.example.live_stream_demo.service.IvsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ivs.IvsClient;
import software.amazon.awssdk.services.ivs.model.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IvsServiceImpl implements IvsService {
    private final IvsClient ivsClient;

    @Override
    public IvsUrlDto createChannel(String channelName) {
        CreateChannelRequest request = CreateChannelRequest.builder()
                .name(channelName)
                .type(ChannelType.BASIC)
                .latencyMode(ChannelLatencyMode.NORMAL)
                .build();
        CreateChannelResponse response = ivsClient.createChannel(request);

        return IvsUrlDto.builder()
                .channelArn(response.channel().arn())
                .ingestUrl("rtmps://" + response.channel().ingestEndpoint() + ":443/app/")
                .streamKey(response.streamKey().value())
                .build();
    }

    @Override
    public String getLiveStreamUrls(String channelArn) {
        try {
            GetChannelRequest request = GetChannelRequest.builder()
                    .arn(channelArn)
                    .build();
            GetChannelResponse response = ivsClient.getChannel(request);

            return response.channel().playbackUrl();
        } catch (AwsServiceException | SdkClientException e) {
            log.error("Error retrieving playback url for channel: {}", channelArn);
            throw new RecordNotFoundException(e.getMessage());
        }
    }

    private void getLiveChat()  {

    }

}
