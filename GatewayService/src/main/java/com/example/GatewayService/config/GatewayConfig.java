package com.example.GatewayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {{
        return builder.routes()
                .route("category-service", r -> r.path("/api/category/**")
                        .filters(f -> f.circuitBreaker(cb -> cb.setName("categoryServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback"))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),
                                                Duration.ofMillis(800),
                                                2,
                                                true))
                        )
                        .uri("lb://category-service"))
                .route("expense-service", r -> r.path("/api/expense/**")
                        .filters(f -> f.circuitBreaker(cb -> cb.setName("expenseServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback"))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),
                                                Duration.ofMillis(800),
                                                2,
                                                true)))
                        .uri("lb://expense-service"))

//                OpenAPI routes for Swagger documentation
                .route("category-swagger",r -> r.path("/api-docs/category")
                        .filters(f -> f.rewritePath("/api-docs/category", "/api-docs"))
                        .uri("lb://category-service"))
                .route("expense-swagger",r -> r.path("/api-docs/expense")
                        .filters(f -> f.rewritePath("/api-docs/expense", "/api-docs"))
                        .uri("lb://expense-service"))
                // Fallback route
                .route("fallback-route", r -> r.path("/fallback")
                        .uri("forward:/fallback-handler"))
                .build();
    }}
}
