package com.hostel.authservice.service;

import com.hostel.authservice.dto.CreateStudentResDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginResWithTokenStudentDto;
import com.hostel.authservice.dto.StudentDto;
import com.hostel.authservice.dto.StudentRegisterDto;
import com.hostel.authservice.dto.User;

public interface StudentAuthService {
	LoginResWithTokenStudentDto login(LoginReq request);
	
	
	CreateStudentResDto register(StudentRegisterDto request);

	void changePassword(long userid, String password);
	StudentDto loggedIn(String token);


	
}
