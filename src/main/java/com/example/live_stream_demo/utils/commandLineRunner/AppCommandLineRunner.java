package com.example.live_stream_demo.utils.commandLineRunner;

import com.example.live_stream_demo.service.RoleService;
import com.example.live_stream_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
@Slf4j
public class AppCommandLineRunner implements CommandLineRunner {

    private final RoleService roleService;

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking roles...");
        roleService.checkRoles();

        log.info("Checking admin...");
        userService.checkAdmin();
    }
}
