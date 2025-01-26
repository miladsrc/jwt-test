package com.jwttest.config;

import com.jwttest.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtService jwtService;

    @Override
    public void doFilter(@NonNull ServletRequest request,
                         @NonNull ServletResponse response,
                         @NonNull FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

            String token = authorizationHeader.substring(7); // حذف "Bearer "
            final String userEmail = jwtService.extractUsername(token);



//            try {
//                // اعتبارسنجی توکن (مثلاً با استفاده از JwtUtils یا JwtTokenProvider)
//                Claims claims = Jwt.validateToken(token);
//
//                // ایجاد Authentication در SecurityContext
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        claims.getSubject(), null, new ArrayList<>() // اختیاری: اضافه کردن نقش‌ها
//                );
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            } catch (JwtException ex) {
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
//                return;
//            }
    }

}
