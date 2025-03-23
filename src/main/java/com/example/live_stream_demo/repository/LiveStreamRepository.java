package com.example.live_stream_demo.repository;

import com.example.live_stream_demo.model.entity.LiveStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveStreamRepository extends JpaRepository<LiveStream, Long> {
}
