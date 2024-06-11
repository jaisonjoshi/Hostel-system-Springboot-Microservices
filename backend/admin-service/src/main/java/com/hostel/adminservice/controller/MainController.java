package com.hostel.adminservice.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.adminservice.dto.AdminRegisterDto;
import com.hostel.adminservice.dto.UpdateHostelDto;
import com.hostel.adminservice.exception.ResourceNotFoundException;
import com.hostel.adminservice.model.Hostel;
import com.hostel.adminservice.service.MainService;

import ch.qos.logback.core.status.Status;




@RestController
@RequestMapping("/api/admin")

public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	private MainService mainService;
	
	public MainController(MainService mainService) {
		super();
		this.mainService = mainService;
	}


	
	//Function for getting details based on email
	
	@PostMapping("/details")
	public ResponseEntity<Hostel> getDetailsByEmail(@RequestBody String email) {
		try {
			return ResponseEntity.ok().body(mainService.findByEmail(email));
		}
		catch(ResourceNotFoundException e) {
			logger.error("Resource yu are looking for is not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	
	
	
	
	
	//Function for creating hostel
	
	@PostMapping("/register")
	public void createHostel(@RequestBody AdminRegisterDto req) {
		try {
			Hostel hostel = new Hostel(req.getHostelName(), req.getName(), req.getEmail(),req.getPhone(), req.getPassword());
			mainService.createHostel(hostel);
		}
		catch(Exception e) {
			logger.error("Some error happened. Please try again later.");
			throw e;
		}
		
	}
	
	
	@PutMapping("/details/update")
	public ResponseEntity<?> updateHostel(@RequestBody UpdateHostelDto req) {
		Hostel updatedHostel = mainService.updateHostel(req);
		if(updatedHostel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hostel not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(updatedHostel);
	}
	
		
}






