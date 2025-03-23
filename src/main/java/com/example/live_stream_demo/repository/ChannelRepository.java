package com.example.live_stream_demo.repository;

import com.example.live_stream_demo.model.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    boolean existsByName(String name);
}
