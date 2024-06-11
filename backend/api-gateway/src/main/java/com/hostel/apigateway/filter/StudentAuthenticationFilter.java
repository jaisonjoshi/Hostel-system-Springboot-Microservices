package com.hostel.apigateway.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;







@Component
public class StudentAuthenticationFilter extends AbstractGatewayFilterFactory<StudentAuthenticationFilter.Config>{

	@Autowired
	public RouteValidator validator;
	
	private WebClient webClient;
	
	public StudentAuthenticationFilter(WebClient webClient) {
		super(Config.class);
		this.webClient = webClient;

	}
	
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(validator.isSecured.test(exchange.getRequest())) {
				
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorization header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				System.out.println(authHeader);

				
				try {
					
		
					Mono<Boolean> isValid =  webClient.get()
							
							.uri("http://localhost:8003/auth/student/validatetoken/"+ authHeader.substring(7))
							.retrieve()
							.bodyToMono(Boolean.class)
							.doOnNext(valid -> {
								if(!valid) {
									throw new RuntimeException("unauthorized");
								}
							});
					
					Mono<String> role = webClient.get()
							.uri("http://localhost:8003/auth/student/validatetoken/getrole"+ authHeader.substring(7))
							.retrieve()
							.bodyToMono(String.class)
							.doOnNext(userrole -> {
								if(!"USER".equals(userrole)) {
									throw new RuntimeException("Unauthorized application to admin panel");
								}
							});

							
					isValid.subscribe();
					role.subscribe();
						
					
				}
				catch(Exception e) {
					System.out.println(e);
					throw new RuntimeException("unauthorized access to application");
				}
			}
			return chain.filter(exchange);
			
			
		});
	}
	
	public static class Config {
		
	}
}
