package com.soc.hotel.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String API_KEY_SCHEME_NAME = "ApiKeyAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel & Room Service API - Student 2")
                        .version("1.0.0")
                        .description("Microservice for managing hotel listings, room types, pricing, and availability. Part of the Service-Oriented Computing (SOC) Group Project.")
                        .contact(new Contact()
                                .name("Student 2 - Hotel & Room Service Team Lead")
                                .email("student2@socproject.com")))
                .addSecurityItem(new SecurityRequirement().addList(API_KEY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(API_KEY_SCHEME_NAME, new SecurityScheme()
                                .name("X-API-KEY")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Enter the API Key value: hotel-service-secret-key-123")));
    }
}
