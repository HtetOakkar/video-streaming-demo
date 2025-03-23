package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.exception.ApplicationException;
import com.example.live_stream_demo.exception.UserNotFoundException;
import com.example.live_stream_demo.model.entity.Role;
import com.example.live_stream_demo.model.entity.User;
import com.example.live_stream_demo.model.enums.RoleName;
import com.example.live_stream_demo.repository.RoleRepository;
import com.example.live_stream_demo.repository.UserRepository;
import com.example.live_stream_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void checkAdmin() {
        Optional<User> admin = userRepository.findByUsername("admin");

        if (admin.isPresent()) {
            log.info("Admin account already created.");
            return;
        }
        log.info("Admin account has not created yet. Creating.....");
        String password = passwordEncoder.encode("Admin123");
        User user = new User();
        user.setUsername("admin");
        user.setPassword(password);
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
        List<Role> roles = Collections.singletonList(adminRole);
        user.setRoles(roles);
        userRepository.saveAndFlush(user);
        log.info("Admin account has been created.");
    }

    @Override
    public void upgradeToBroadcaster(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        Role broadcasterRole = roleRepository.findByName(RoleName.ROLE_BROADCASTER);
        List<Role> roles = user.getRoles();
        roles.add(broadcasterRole);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
