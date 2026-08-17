package com.soc.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String bearerAuthScheme = "bearerAuth";
        final String apiKeyScheme = "apiKeyHeader";

        return new OpenAPI()
                .info(new Info()
                        .title("SOC Student 1 - API Gateway & Auth Service")
                        .version("1.0.0")
                        .description("Microservices Architecture - Student 1 Implementation (User Management, OAuth 2.0/JWT Auth, Rate Limiting, API Key Security)")
                        .contact(new Contact()
                                .name("Student 1 - Gateway Lead")
                                .email("student1@example.com")))
                .addSecurityItem(new SecurityRequirement().addList(bearerAuthScheme).addList(apiKeyScheme))
                .components(new Components()
                        .addSecuritySchemes(bearerAuthScheme, new SecurityScheme()
                                .name(bearerAuthScheme)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter JWT Token obtained from /auth/login"))
                        .addSecuritySchemes(apiKeyScheme, new SecurityScheme()
                                .name("X-API-KEY")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Enter global API key (Default: SOC-SECRET-API-KEY-2026) or User API Key")));
    }
}
