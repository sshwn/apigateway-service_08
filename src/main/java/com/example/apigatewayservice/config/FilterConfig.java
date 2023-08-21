package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                            .filters(f -> f.addRequestHeader("first-request","first-request-header")
                                           .addResponseHeader("first-response","first-response-header"))
                            .uri("http://localhost:8081"))
                // 1. r.path("/first-service/**") 사용자로부터 first-service 요청이 들어온다
                // 2. uri("http://localhost:8081")) uri로 이동을 할건데 중간에
                // 3. addRequestHeader  addResponseHeader  에 추가한다.
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request","second-request-header")
                                       .addResponseHeader("second-response","second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
