package com.jwttest.config;

import lombok.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(@NonNull ServletRequest request,
                         @NonNull ServletResponse response,
                         @NonNull FilterChain chain)
            throws IOException, ServletException {

    }

}
