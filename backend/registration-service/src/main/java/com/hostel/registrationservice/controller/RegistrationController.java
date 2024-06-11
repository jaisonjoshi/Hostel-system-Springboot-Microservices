package com.hostel.registrationservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.registrationservice.dto.RegistrationRequestDto;
import com.hostel.registrationservice.model.RegistrationItem;
import com.hostel.registrationservice.service.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
	
	private RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}



	
	
	
	@PostMapping("/")
	public ResponseEntity<RegistrationItem> registerNewStudent(@RequestBody RegistrationRequestDto req) {
		try {
			return ResponseEntity.ok().body(registrationService.registerNewStudent(req));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	
	
}
