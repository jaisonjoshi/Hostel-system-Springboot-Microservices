package com.hostel.registrationservice.service;

import com.hostel.registrationservice.dto.RegistrationRequestDto;
import com.hostel.registrationservice.model.RegistrationItem;

public interface RegistrationService {
	
	RegistrationItem registerNewStudent(RegistrationRequestDto req);
}
