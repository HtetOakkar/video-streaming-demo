package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.model.entity.Role;
import com.example.live_stream_demo.model.enums.RoleName;
import com.example.live_stream_demo.repository.RoleRepository;
import com.example.live_stream_demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public void checkRoles() {
        if (roleRepository.count() == 0) {
            log.info("Initializing roles..");
            initializeRoles();
            log.info("Roles initialized successfully");
        }
        log.info("Roles already initialized");
    }

    private void initializeRoles() {
        List<Role> roles = new ArrayList<>();
        Role adminRole = new Role();
        adminRole.setName(RoleName.ROLE_ADMIN);
        roles.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleName.ROLE_USER);
        roles.add(userRole);
        Role broadcasterRole = new Role();
        broadcasterRole.setName(RoleName.ROLE_BROADCASTER);
        roles.add(broadcasterRole);
        roleRepository.saveAll(roles);
    }
}
