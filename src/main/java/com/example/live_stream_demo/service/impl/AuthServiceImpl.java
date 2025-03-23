package com.example.live_stream_demo.service.impl;

import com.example.live_stream_demo.exception.BadRequestException;
import com.example.live_stream_demo.exception.ForbiddenException;
import com.example.live_stream_demo.model.entity.Role;
import com.example.live_stream_demo.model.entity.User;
import com.example.live_stream_demo.model.enums.RoleName;
import com.example.live_stream_demo.model.payload.request.AccountRegisterRequest;
import com.example.live_stream_demo.model.payload.request.LoginRequest;
import com.example.live_stream_demo.model.payload.response.TokenResponse;
import com.example.live_stream_demo.repository.RoleRepository;
import com.example.live_stream_demo.repository.UserRepository;
import com.example.live_stream_demo.security.JwtService;
import com.example.live_stream_demo.security.UserPrincipal;
import com.example.live_stream_demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public TokenResponse authenticateUser(LoginRequest request) {
        return getTokenResponse(request, RoleName.ROLE_USER.name());
    }

    @Override
    public TokenResponse authenticateAdmin(LoginRequest request) {
        return getTokenResponse(request, RoleName.ROLE_ADMIN.name());
    }

    @Override
    public void registerUser(AccountRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        boolean isUserExist = userRepository.existsByUsername(username);
        if (isUserExist) {
            throw new BadRequestException("Username already taken.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
        List<Role> roles = Collections.singletonList(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    private TokenResponse getTokenResponse(LoginRequest request, String roleName) {
        Date expiration = getExpirationTime();

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(roleName))) {
            String jwt = jwtService.generateToken(authentication);
            return new TokenResponse(jwt, expiration);
        } else {
            throw new ForbiddenException("You cannot login from here.");
        }
    }


    private Date getExpirationTime() {
        return new Date(new Date().getTime() + 86400 * 1000);
    }
}
