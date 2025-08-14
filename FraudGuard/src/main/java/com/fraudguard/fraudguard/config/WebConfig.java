package com.fraudguard.fraudguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/") // applies to all endpoints
//                        .allowedOrigins("http://localhost:8081", "http://192.168.43.16:8081") // frontend addresses
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*");

                registry.addMapping("/**") // <-- allow all routes
                        .allowedOrigins("http://localhost:8081", "http://192.168.43.16:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");

            }
        };
    }

}
