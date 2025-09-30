package com.jaba.heraldism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main application class for Dota Service.
 * 
 * This service provides player and match management functionality
 * for Dota 2, including match scoring and draft advice.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class DotaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DotaServiceApplication.class, args);
    }
}
