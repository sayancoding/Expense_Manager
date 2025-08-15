package com.example.GatewayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
     @Bean
     public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
         return builder.routes()
             .route("expense_service", r -> r.path("/api/expense/**")
                 .uri("lb://expense-service"))
             .route("category_service", r -> r.path("/api/category/**")
                 .uri("lb://category-service"))
             .build();
     }

//    Config for Gateway-server-mvc dependency
//    @Bean
//    public RouterFunction<ServerResponse> customRouteLocator() {
//        return GatewayMvcRouterFunctions.route("product-service-route") // Route ID
//                .route(path("/api/products/**"), HandlerFunctions.http("lb://product-service"))
//                .route(path("/api/users/**"), HandlerFunctions.http("lb://user-service"))
//                .build();
//    }
}
