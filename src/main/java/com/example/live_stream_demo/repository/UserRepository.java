package com.example.live_stream_demo.repository;

import com.example.live_stream_demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String admin);

    boolean existsByUsername(String username);
}
