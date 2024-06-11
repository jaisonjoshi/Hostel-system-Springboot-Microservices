package com.hostel.authservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.hostel.authservice.dto.AdminRegisterDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginRes;
import com.hostel.authservice.dto.LoginResWithTokenDto;
import com.hostel.authservice.dto.User;
import com.hostel.authservice.service.AdminAuthService;
import com.hostel.authservice.util.JwtUtil;

import reactor.core.publisher.Mono;


@Service
public class AdminAuthServiceImpl implements AdminAuthService{

	private WebClient webClient;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	public AdminAuthServiceImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}
	
	
	
	
	//Register function
	
	public void register(AdminRegisterDto request) {
		Mono<AdminRegisterDto> obj = Mono.just(request);
		 webClient.post()
				.uri("http://localhost:8001/api/admin/register")
				.body(obj, AdminRegisterDto.class)
				.retrieve()
				.bodyToMono(Void.class)
				.block();		
	}
	
	
	
	
	
	//Login function
	
	public LoginResWithTokenDto login(LoginReq request) {
		LoginResWithTokenDto obj = new LoginResWithTokenDto();
	
		LoginRes userdetails = webClient.post()
			.uri("http://localhost:8001/api/admin/details")
			.body(BodyInserters.fromValue(request.getEmail()))
			.retrieve()
			.bodyToMono(LoginRes.class)
			.block();
			if(userdetails != null && userdetails.getName() != null) {
				User user = new User(userdetails.getId(), userdetails.getHostelName(),userdetails.getName(), userdetails.getEmail(), userdetails.getPhone());
				if(passwordEncoder.matches(request.getPassword(), userdetails.getPassword())) {
					String token = jwtUtil.generateToken(userdetails.getEmail(), "ADMIN");
					obj.setToken(token);
					obj.setUser(user);
					return obj;
				}
			}
			
			return null;
			
	}
	
	
	




	@Override
	public User loggedIn(String token) {
		Boolean isValid = jwtUtil.validateToken(token);
		System.out.println(isValid);
		if(isValid) {
			String email = jwtUtil.decodeEmailFromJWTtoken(token);
			System.out.println(email);
			LoginRes user = webClient.post()
					.uri("http://localhost:8001/api/admin/details")
					.body(BodyInserters.fromValue(email))
					.retrieve()
					.bodyToMono(LoginRes.class)
					.block();
			
			
			if(user != null) {
				User resUser = new User(user.getId(), user.getHostelName(),user.getName(), user.getEmail(), user.getPhone());

				return resUser;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
