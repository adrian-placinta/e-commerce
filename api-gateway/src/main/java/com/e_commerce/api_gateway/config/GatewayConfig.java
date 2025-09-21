package com.e_commerce.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("product-service", r -> r.path("/api/v1/products/**")
                        .uri("http://localhost:8080"))
                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
                        .uri("http://localhost:8085"))
                .build();
    }
}
