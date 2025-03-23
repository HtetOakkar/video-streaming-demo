package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.exception.RecordNotFoundException;
import com.example.live_stream_demo.exception.UnauthorizedException;
import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.entity.Channel;
import com.example.live_stream_demo.model.entity.LiveStream;
import com.example.live_stream_demo.model.entity.User;
import com.example.live_stream_demo.model.mapper.LiveStreamMapper;
import com.example.live_stream_demo.model.payload.request.LiveStreamRequest;
import com.example.live_stream_demo.model.payload.response.LiveStreamCreateResponse;
import com.example.live_stream_demo.repository.ChannelRepository;
import com.example.live_stream_demo.repository.LiveStreamRepository;
import com.example.live_stream_demo.repository.UserRepository;
import com.example.live_stream_demo.service.IvsService;
import com.example.live_stream_demo.service.LiveStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ivs.IvsClient;

@Service
@RequiredArgsConstructor
public class LiveStreamServiceImpl implements LiveStreamService {

    private final LiveStreamRepository liveStreamRepository;

    private final LiveStreamMapper liveStreamMapper;

    private final ChannelRepository channelRepository;

    private final IvsService ivsService;


    @Override
    public LiveStreamDto createLiveStream(Long channelId, Long userId, LiveStreamRequest request) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new RecordNotFoundException("Channel not found with Id: " + channelId));

        if (!channel.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to create live stream on this channel.");
        }

        String playbackUrl = ivsService.getLiveStreamUrls(channel.getArn());
        LiveStream liveStream = new LiveStream();
        liveStream.setTitle(request.getTitle());
        liveStream.setDescription(request.getDescription());
        liveStream.setPlaybackUrl(playbackUrl);
        liveStream.setChannel(channel);
        LiveStream savedLiveStream = liveStreamRepository.save(liveStream);

        return liveStreamMapper.toDto(savedLiveStream);
    }

    @Override
    public LiveStreamDto getLiveStream(Long id) {
        LiveStream liveStream = getLiveStreamEntity(id);
        return liveStreamMapper.toDto(liveStream);
    }

    private LiveStream getLiveStreamEntity(Long id) {
        return liveStreamRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Live stream not found with Id: " + id));
    }
}
