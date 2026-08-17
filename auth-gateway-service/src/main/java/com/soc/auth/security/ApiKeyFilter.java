package com.soc.auth.security;

import com.soc.auth.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${apikey.header:X-API-KEY}")
    private String apiKeyHeader;

    @Value("${apikey.secret:SOC-SECRET-API-KEY-2026}")
    private String configuredSecretApiKey;

    private final UserRepository userRepository;

    public ApiKeyFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Bypass swagger and API docs from API Key check for easy documentation access
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/api-docs") || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestApiKey = request.getHeader(apiKeyHeader);

        if (requestApiKey == null || requestApiKey.trim().isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Missing API Key header: " + apiKeyHeader + "\"}");
            return;
        }

        boolean isValidGlobalKey = configuredSecretApiKey.equals(requestApiKey);
        boolean isValidUserKey = userRepository.findByApiKey(requestApiKey).isPresent();

        if (!isValidGlobalKey && !isValidUserKey) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Invalid API Key provided\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
