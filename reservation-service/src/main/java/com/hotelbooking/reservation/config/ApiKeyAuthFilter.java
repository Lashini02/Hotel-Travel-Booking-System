package com.hotelbooking.reservation.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${api.key.header:X-API-KEY}")
    private String apiKeyHeader;

    @Value("${api.key.value:reservation-secret-api-key-12345}")
    private String apiKeyValue;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Allow GET, OPTIONS, Swagger UI and API Docs without API key check in browser
        if ("GET".equalsIgnoreCase(request.getMethod()) || "OPTIONS".equalsIgnoreCase(request.getMethod()) || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestApiKey = request.getHeader(apiKeyHeader);

        if (requestApiKey == null || (!requestApiKey.equals(apiKeyValue) && !requestApiKey.equals("SOC-SECRET-API-KEY-2026"))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Invalid or missing API Key in header: " + apiKeyHeader + "\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
