package com.hotel.review.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter implements Filter {

    @Value("${api.key.secret}")
    private String expectedApiKey;

    @Value("${api.key.header}")
    private String apiKeyHeaderName;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestPath = httpRequest.getRequestURI();
        
        // Allow Swagger UI without API Key for testing/documentation
        if (requestPath.startsWith("/v3/api-docs") || requestPath.startsWith("/swagger-ui")) {
            chain.doFilter(request, response);
            return;
        }

        String providedApiKey = httpRequest.getHeader(apiKeyHeaderName);

        if (providedApiKey != null && providedApiKey.equals(expectedApiKey)) {
            // API Key is correct, proceed to the endpoint
            chain.doFilter(request, response);
        } else {
            // API Key is missing or invalid, reject the request
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Invalid or Missing API Key");
        }
    }
}
