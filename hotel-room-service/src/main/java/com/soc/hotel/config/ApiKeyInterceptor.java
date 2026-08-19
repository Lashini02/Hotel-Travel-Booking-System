package com.soc.hotel.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Value("${security.api-key:hotel-service-secret-key-123}")
    private String expectedApiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // Bypass security check for Swagger UI and OpenAPI documentation
        if (path.contains("/swagger-ui") || 
            path.contains("/v3/api-docs") || 
            path.contains("/actuator") || 
            path.equals("/") || 
            path.equals("/error")) {
            return true;
        }

        // Check header for API key verification
        String requestApiKey = request.getHeader(API_KEY_HEADER);

        if (requestApiKey == null || !requestApiKey.equals(expectedApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Unauthorized: Invalid or missing X-API-KEY header.\", \"timestamp\": \"" + java.time.LocalDateTime.now() + "\"}");
            return false;
        }

        return true;
    }
}
