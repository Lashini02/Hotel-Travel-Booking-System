package com.hotelbooking.reservation.config;

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

    private static final String API_KEY_SCHEME_NAME = "ApiKeyAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Reservation Service API - Student 3")
                        .version("1.0.0")
                        .description("Microservice for managing hotel bookings, reservation stay dates, cancellations, and booking history.")
                        .contact(new Contact()
                                .name("Student 3")
                                .email("student3@horizoncampus.edu.lk")))
                .addSecurityItem(new SecurityRequirement().addList(API_KEY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(API_KEY_SCHEME_NAME, new SecurityScheme()
                                .name("X-API-KEY")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Required API Key header for microservice authentication")));
    }
}
