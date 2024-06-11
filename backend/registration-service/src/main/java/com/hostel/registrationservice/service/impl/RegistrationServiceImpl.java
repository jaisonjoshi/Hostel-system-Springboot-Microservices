package com.hostel.registrationservice.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hostel.registrationservice.dto.CreateStudentRequestDto;
import com.hostel.registrationservice.dto.CreateStudentResponseDto;
import com.hostel.registrationservice.dto.RegistrationRequestDto;
import com.hostel.registrationservice.model.RegistrationItem;
import com.hostel.registrationservice.repository.RegistrationRepository;
import com.hostel.registrationservice.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	
	private WebClient webClient;
	
	
	private RegistrationRepository registrationRepository;
	
	
	
	public RegistrationServiceImpl(WebClient webClient, RegistrationRepository registrationRepository) {
		super();
		this.webClient = webClient;
		this.registrationRepository = registrationRepository;
	}



	public RegistrationItem registerNewStudent(RegistrationRequestDto req) {
		
		System.out.println(req.getRoomNo());
		
		CreateStudentRequestDto newStudent = new CreateStudentRequestDto(req.getName(), req.getEmail(), req.getPhone(), req.getPassword(), req.getRoomNo());
		CreateStudentResponseDto createdStudent = webClient.post()
				.uri("http://localhost:8003/auth/student/register")
				.bodyValue(newStudent)
				.retrieve()
				.bodyToMono(CreateStudentResponseDto.class)
				.block();
		
		int roomNo = webClient.put()
				.uri("http://localhost:8001/api/hostel/rooms/"+ req.getRoomNo())
				.retrieve()
				.bodyToMono(Integer.class)
				.block();
		
		Date date = new Date();
		
		
		RegistrationItem newRegistration = new RegistrationItem(createdStudent.getId(), createdStudent.getName(), roomNo, date);
		return registrationRepository.save(newRegistration);
		
		
		
	}
	
	
	
}
