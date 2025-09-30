package com.jaba.heraldism.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger configuration.
 */
@Configuration
public class OpenApiConfig {
    
    @Value("${spring.application.name:dota-service}")
    private String applicationName;
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dota Service API")
                        .version("1.0.0")
                        .description("API for managing Dota 2 players, matches, and providing draft advice")
                        .contact(new Contact()
                                .name("Heraldism Team")
                                .email("support@heraldism.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080/api")
                                .description("Development server"),
                        new Server()
                                .url("https://api.heraldism.com/api")
                                .description("Production server")
                ));
    }
}
