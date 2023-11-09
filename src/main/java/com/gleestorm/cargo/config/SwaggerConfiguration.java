package com.gleestorm.cargo.config;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springSwaggerExample() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(
                                "api",
                                new SecurityScheme()
                                        .scheme("bearer")
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat("jwt") //if it is your case
                                        .name("api")
                        )
                ).info(new Info().title("Cargo API Documentation")
                        .description("Cargo API Documentation")
                        .version("v0.0.1"));
    }
}