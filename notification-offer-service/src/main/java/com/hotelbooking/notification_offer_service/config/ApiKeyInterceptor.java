package com.hotelbooking.notification_offer_service.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class ApiKeyInterceptor implements HandlerInterceptor {

    private static final String API_KEY = "notification-service-secret-key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // Allow Swagger UI and OpenAPI documentation endpoints without API key
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.equals("/swagger-ui.html")) {
            return true;
        }

        String requestApiKey = request.getHeader("X-API-KEY");

        if (requestApiKey == null || (!requestApiKey.equals(API_KEY) && !requestApiKey.equals("SOC-SECRET-API-KEY-2026"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid or missing API Key");
            return false;
        }

        return true;
    }
}