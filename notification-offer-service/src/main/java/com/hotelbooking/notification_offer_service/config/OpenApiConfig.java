package com.hotelbooking.notification_offer_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Notification & Offer Service API", version = "v1.0"),
    security = @SecurityRequirement(name = "ApiKeyAuth")
)
@SecurityScheme(
    name = "ApiKeyAuth",
    type = SecuritySchemeType.APIKEY,
    in = io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER,
    paramName = "X-API-KEY"
)
public class OpenApiConfig {
}