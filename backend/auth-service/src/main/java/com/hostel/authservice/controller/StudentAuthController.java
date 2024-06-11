package com.hostel.authservice.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.authservice.dto.AdminRegisterDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginRes;
import com.hostel.authservice.dto.LoginResWithTokenStudentDto;
import com.hostel.authservice.dto.StudentDto;
import com.hostel.authservice.dto.StudentRegisterDto;
import com.hostel.authservice.dto.User;
import com.hostel.authservice.service.AdminAuthService;
import com.hostel.authservice.service.StudentAuthService;
import com.hostel.authservice.util.JwtUtil;

import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/auth/student")
public class StudentAuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private StudentAuthService studentAuthService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public StudentAuthController(StudentAuthService studentAuthService) {
		super();
		this.studentAuthService = studentAuthService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@RequestBody StudentRegisterDto request){

		String encryptedPassword = passwordEncoder.encode(request.getPassword());
		request.setPassword(encryptedPassword);
		
		return ResponseEntity.ok().body(studentAuthService.register(request));
	}
	
	

	@PostMapping("/changepassword/{userid}")
	public ResponseEntity<String> changePassword(@PathVariable long userid,@RequestBody String password){
		
		studentAuthService.changePassword(userid, password);
		return ResponseEntity.ok("Password Updated Successfully");
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginReq loginReq){
		
		
		LoginResWithTokenStudentDto obj = studentAuthService.login(loginReq);
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	
	@GetMapping("/validatetoken/{token}")
	public Boolean validateToken(@PathVariable String token){
		
		Boolean isValid = jwtUtil.validateToken(token);
		System.out.println("hello" + token);
		System.out.println(isValid);
		return isValid;
		
		
		
	}
	
	
	@GetMapping("/validatetoken/getrole/{token}")
	public String decodeRoleFromJWTtoken(@PathVariable String token) {
		return jwtUtil.decodeRoleFromJWTtoken(token);
	}
	
	
	
	@GetMapping("/loggedin")
	public ResponseEntity<?> loggedIn(@RequestHeader(name="Authorization") String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			String token = authorizationHeader.substring(7);
			StudentDto res = studentAuthService.loggedIn(token);
			
			if(res == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(res);
			
			
	        
	        
	        

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorised");
		 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
