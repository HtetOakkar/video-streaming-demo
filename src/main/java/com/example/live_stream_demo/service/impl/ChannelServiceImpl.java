package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.exception.BadRequestException;
import com.example.live_stream_demo.exception.RecordNotFoundException;
import com.example.live_stream_demo.model.dto.ChannelDto;
import com.example.live_stream_demo.model.dto.IvsUrlDto;
import com.example.live_stream_demo.model.entity.Channel;
import com.example.live_stream_demo.model.entity.User;
import com.example.live_stream_demo.model.mapper.ChannelMapper;
import com.example.live_stream_demo.model.payload.request.ChannelCreationRequest;
import com.example.live_stream_demo.repository.ChannelRepository;
import com.example.live_stream_demo.repository.UserRepository;
import com.example.live_stream_demo.service.ChannelService;
import com.example.live_stream_demo.service.IvsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ivs.IvsClient;
import software.amazon.awssdk.services.ivs.model.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    private final UserRepository userRepository;

    private final IvsService ivsService;

    private final ChannelMapper channelMapper;


    @Override
    public ChannelDto createChannel(ChannelCreationRequest request, Long id) {
        String channelName = request.getChannelName();
        boolean existByName = channelRepository.existsByName(channelName);
        if (existByName) {
            throw new BadRequestException("Channel name already exist.");
        }

        IvsUrlDto ivsUrlDto = ivsService.createChannel(channelName);
        String channelArn = ivsUrlDto.getChannelArn();
        String ingestUrl = ivsUrlDto.getIngestUrl();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with Id: " + id));
        Channel channel = new Channel();
        channel.setName(channelName);
        channel.setArn(channelArn);
        channel.setIngestUrl(ingestUrl);
        channel.setUser(user);
        Channel savedChannel = channelRepository.save(channel);
        return channelMapper.toDto(savedChannel);
    }

    @Override
    public ChannelDto getChannel(Long id) {
        Channel channel = getChannelEntity(id);
        return channelMapper.toDto(channel);
    }

    private Channel getChannelEntity(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Channel not found with ID: " + id));
    }
}
