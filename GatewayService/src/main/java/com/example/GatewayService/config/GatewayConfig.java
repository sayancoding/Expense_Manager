package com.example.GatewayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {{
        return builder.routes()
                .route("category-service", r -> r.path("/api/category/**")
                        .uri("lb://category-service"))
                .route("expense-service", r -> r.path("/api/expense/**")
                        .uri("lb://expense-service"))

//                OpenAPI routes for Swagger documentation
                .route("category-swagger",r -> r.path("/api-docs/category")
                        .filters(f -> f.rewritePath("/api-docs/category", "/api-docs"))
                        .uri("lb://category-service"))
                .route("expense-swagger",r -> r.path("/api-docs/expense")
                        .filters(f -> f.rewritePath("/api-docs/expense", "/api-docs"))
                        .uri("lb://expense-service"))
                .build();
    }}
}
