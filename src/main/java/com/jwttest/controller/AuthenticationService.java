package com.jwttest.controller;

import com.jwttest.bean.Role;
import com.jwttest.bean.User;
import com.jwttest.repository.UserRepository;
import com.jwttest.service.JwtService;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest  registerRequest) {
        var user = User
                .builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(
                   authenticationRequest.getEmail(),
                   authenticationRequest.getPassword()
           )
        );
        var user = userRepository.findUserByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
