package com.example.live_stream_demo.model.mapper;

import com.example.live_stream_demo.model.dto.LiveStreamDto;
import com.example.live_stream_demo.model.entity.LiveStream;
import com.example.live_stream_demo.model.payload.response.LiveStreamCreateResponse;
import com.example.live_stream_demo.model.payload.response.LiveStreamViewResponse;

public interface LiveStreamMapper {
    LiveStreamDto toDto(LiveStream liveStream);

    LiveStreamCreateResponse toCreateResponse(LiveStreamDto liveStreamDto);

    LiveStreamViewResponse toViewResponse(LiveStreamDto liveStreamDto);
}
