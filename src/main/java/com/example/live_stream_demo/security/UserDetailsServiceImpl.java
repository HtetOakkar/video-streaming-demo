package com.example.live_stream_demo.security;

import com.example.live_stream_demo.exception.UserNotFoundException;
import com.example.live_stream_demo.model.entity.User;
import com.example.live_stream_demo.repository.UserRepository;
import com.example.live_stream_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: {}" + username));

        return UserPrincipal.build(user);
    }
}
