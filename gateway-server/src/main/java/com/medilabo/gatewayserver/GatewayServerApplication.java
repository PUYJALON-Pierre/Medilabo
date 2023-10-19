package com.medilabo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	
	        return builder.routes()
	                .route("patient-ms", r -> r.path("/patient/**")
	                        .uri("lb://PATIENT-MS"))
	                .route("client-ui", r -> r.path("/client/**")
	                        .uri("lb://CLIENT-UI"))
	                .build();   
	}
	
	
	@Bean
	public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
	return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
	}
	
	
	
}