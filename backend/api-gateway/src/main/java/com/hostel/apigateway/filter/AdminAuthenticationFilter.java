package com.hostel.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Component
public class AdminAuthenticationFilter extends AbstractGatewayFilterFactory<AdminAuthenticationFilter.Config>{
	
	@Autowired
	private WebClient webClient;
	
	public AdminAuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain)-> {
			 String token = extractToken(exchange.getRequest().getHeaders().getFirst("Authorization"));

			  // Validate JWT token
			 
			 return isValidToken(token)
					 .flatMap(isValid -> {
						 if(isValid) {
							 return extractRoleFromToken(token)
									 .flatMap(role->{
										 if("ADMIN".equalsIgnoreCase(role)) {
											 return chain.filter(exchange);
										 }
										 else if("USER".equalsIgnoreCase(role)) {
											 return chain.filter(exchange);

										 }
										 else {
											 return unauthorizedResponse(exchange); 
										 }
									 });
							
						 }
						 else {
							 return invalidTokenResponse(exchange);
						 }
					 });
//		        if (token == null || !isValidToken(token)) {
//		            return invalidTokenResponse(exchange);
//		        }
		        
//		        
//		        // Extract role from JWT token
//		        String role = extractRoleFromToken(token);
//
//		        if (!"ADMIN".equals(role)) {
//		            return unauthorizedResponse(exchange);
//		        }
//		        
//		        
//		        // Continue with the request chain if everything is valid
//		        return chain.filter(exchange);

			
		});
	}
	
//	@Override
//	 public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		 String token = extractToken(exchange.getRequest().getHeaders().getFirst("Authorization"));
//		 
//		 
//		  // Validate JWT token
//	        if (token == null || !isValidToken(token)) {
//	            return invalidTokenResponse(exchange);
//	        }
//	        
//	        
//	        // Extract role from JWT token
//	        String role = extractRoleFromToken(token);
//	        
//	        
//	        
//	        if (!"ADMIN".equals(role)) {
//	            return unauthorizedResponse(exchange);
//	        }
//	        
//	        
//	        // Continue with the request chain if everything is valid
//	        return chain.filter(exchange);
//	           
//	        
//	        
//	        
//	}
	
	
	
	
	
	
    public String extractToken(String authHeader) {
    	if(authHeader != null && authHeader.startsWith("Bearer ")) {
    		return authHeader.substring(7);
    	}
    	return null;
    }
    
    
    // Method to validate JWT token
    public Mono<Boolean> isValidToken(String token) {
        // Implement token validation logic here
    	String url = "http://localhost:8003/auth/admin/validatetoken/"+ token;
    	
    	return webClient.get()
    			.uri(url)
    			.retrieve()
    			.bodyToMono(boolean.class);


    }
    
    
    // Method to handle invalid token response
    public Mono<Void> invalidTokenResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
    
    
    
    // Method to extract role from JWT token
    public Mono<String> extractRoleFromToken(String token) {
        // Implement role extraction logic here
    	String url = "http://localhost:8003/auth/admin/validatetoken/getrole/"+ token;
    	return webClient.get()
    			.uri(url)
    			.retrieve()
    			.bodyToMono(String.class);
    			
    }
    
    
    // Method to handle unauthorized response
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
    
    
    
    
    
    
    
    public static class Config{
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}