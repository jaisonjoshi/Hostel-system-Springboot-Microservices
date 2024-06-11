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
import org.springframework.web.bind.annotation.RestController;
import com.hostel.authservice.dto.AdminRegisterDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginRes;
import com.hostel.authservice.dto.LoginResWithTokenDto;
import com.hostel.authservice.dto.User;
import com.hostel.authservice.service.AdminAuthService;
import com.hostel.authservice.util.JwtUtil;



@RestController
@RequestMapping("/auth")
public class AdminAuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private AdminAuthService adminAuthService;
	
	public AdminAuthController(AdminAuthService adminAuthService) {
		super();
		this.adminAuthService = adminAuthService;
	}
	
	
	//Admin register function

	@PostMapping("/admin/register")
	public ResponseEntity<String> registerHostel(@RequestBody AdminRegisterDto request){
		try {
			String encryptedPassword = passwordEncoder.encode(request.getPassword());
			request.setPassword(encryptedPassword);
			adminAuthService.register(request);
			return ResponseEntity.ok("Registration successful");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed due to an internal server error. Please try again after some time.");
		}
	}
	
	
	
	
	
	@PostMapping("/admin/login")
	public ResponseEntity<?> login(@RequestBody LoginReq loginReq){
		LoginResWithTokenDto obj = adminAuthService.login(loginReq);
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	
	@GetMapping("/admin/loggedin")
	public ResponseEntity<?> loggedIn(@RequestHeader(name="Authorization") String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			String token = authorizationHeader.substring(7);
			User res = adminAuthService.loggedIn(token);
			
			if(res == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(res);
			
			
	        
	        
	        

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorised");
		 
		
	}
	
	
	
	
	
	@GetMapping("/admin/validatetoken/{token}")
	public Boolean validateToken(@PathVariable String token){
		
		Boolean isValid = jwtUtil.validateToken(token);
		System.out.println("hello" + token);
		System.out.println(isValid);
		return isValid;
		
		
		
	}
	
	
	@GetMapping("/admin/validatetoken/getrole/{token}")
	public String decodeRoleFromJWTtoken(@PathVariable String token) {
		return jwtUtil.decodeRoleFromJWTtoken(token);
	}
	
	
	
	
	
	
	
	
	
}
