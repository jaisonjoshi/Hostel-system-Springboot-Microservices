package com.hostel.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hostel.apigateway.filter.AdminAuthenticationFilter;

@Configuration
public class GatewayConfig {
	
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AdminAuthenticationFilter adminAuthenticationFilter) {
		return builder.routes()
				.route("auth", r -> r
						.path("/auth/**")
						.uri("http://localhost:8003"))
						
				.route("admin", r-> r
						.path("/api/admin/**","/api/hostel/**")
						.filters(f -> f.filter(adminAuthenticationFilter.apply(new AdminAuthenticationFilter.Config())))
						.uri("http://localhost:8001"))
				.route("admin", r-> r
						.path("/api/student/**")
						.filters(f-> f.filter(adminAuthenticationFilter.apply(new AdminAuthenticationFilter.Config())))
						.uri("http://localhost:8007"))
				.route("admin", r -> r
						.path("/api/registration/**")
						.filters(f->f.filter(adminAuthenticationFilter.apply(new AdminAuthenticationFilter.Config())))
						.uri("http://localhost:8002"))
				.route("admin", r -> r
						.path("/api/payment/**")
						.filters(f->f.filter(adminAuthenticationFilter.apply(new AdminAuthenticationFilter.Config())))
						.uri("http://localhost:8006"))
				
				.build();
	}
	
	
}
