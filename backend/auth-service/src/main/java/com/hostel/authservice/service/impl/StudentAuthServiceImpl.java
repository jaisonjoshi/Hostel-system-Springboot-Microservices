package com.hostel.authservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.hostel.authservice.dto.AdminRegisterDto;
import com.hostel.authservice.dto.ChangePasswordRequestDto;
import com.hostel.authservice.dto.CreateStudentResDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginRes;
import com.hostel.authservice.dto.LoginResStudent;
import com.hostel.authservice.dto.LoginResWithTokenDto;
import com.hostel.authservice.dto.LoginResWithTokenStudentDto;
import com.hostel.authservice.dto.StudentDto;
import com.hostel.authservice.dto.StudentRegisterDto;
import com.hostel.authservice.dto.User;
import com.hostel.authservice.service.StudentAuthService;
import com.hostel.authservice.util.JwtUtil;

import reactor.core.publisher.Mono;


@Service
public class StudentAuthServiceImpl implements StudentAuthService{

	private WebClient webClient;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	public StudentAuthServiceImpl(WebClient webClient) {
		super();
		this.webClient = webClient;
	}
	
	
	
	
	
	
	//Register function
	
		public CreateStudentResDto register(StudentRegisterDto request) {
			
			Mono<StudentRegisterDto> obj = Mono.just(request);
			CreateStudentResDto res =  webClient.post()
					.uri("http://localhost:8007/api/student/register")
					.body(obj, AdminRegisterDto.class)
					.retrieve()
					.bodyToMono(CreateStudentResDto.class)
					.block();	
			
			return res;
			
			
		}
		
		
		
		
		@Override
		public StudentDto loggedIn(String token) {
			Boolean isValid = jwtUtil.validateToken(token);
			if(isValid) {
				String email = jwtUtil.decodeEmailFromJWTtoken(token);
				System.out.println(email);
				LoginResStudent user = webClient.post()
						.uri("http://localhost:8007/api/student/details")
						.body(BodyInserters.fromValue(email))
						.retrieve()
						.bodyToMono(LoginResStudent.class)
						.block();
				
				
				if(user != null) {
					StudentDto resUser = new StudentDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRoomNo());

					return resUser;
				}
			}
			return null;
		}
		
		
		
	
	public void changePassword( long userid ,String password) {
		String encryptedPassword = passwordEncoder.encode(password);
		ChangePasswordRequestDto req = new ChangePasswordRequestDto(userid, encryptedPassword);
		 webClient.post()
				.uri("http://localhost:8003/api/student/register")
				.bodyValue(req)
				.retrieve()
				.bodyToMono(Void.class)
				.block();		
	}
	
	
	
	
	
	//Login function
	
	public LoginResWithTokenStudentDto login(LoginReq request) {
		LoginResWithTokenStudentDto obj = new LoginResWithTokenStudentDto();

		LoginResStudent userdetails = webClient.post()
			.uri("http://localhost:8007/api/student/details")
			.body(BodyInserters.fromValue(request.getEmail()))
			.retrieve()
			.bodyToMono(LoginResStudent.class)
			.block();
			if(userdetails != null && userdetails.getName() != null) {
				StudentDto user = new StudentDto(userdetails.getId() ,userdetails.getName(), userdetails.getEmail(), userdetails.getPhone(), userdetails.getRoomNo());
				if(passwordEncoder.matches(request.getPassword(), userdetails.getPassword())) {
					String token = jwtUtil.generateToken(userdetails.getEmail(), "USER");
					obj.setToken(token);
					obj.setUser(user);
					return obj;
				}
			}
			
			return null;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
