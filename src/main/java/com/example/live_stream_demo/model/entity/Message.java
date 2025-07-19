package com.example.live_stream_demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Message {
    private String content;
    private Instant sentAt;
}
