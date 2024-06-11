package com.hostel.authservice.service;

import org.springframework.stereotype.Service;

import com.hostel.authservice.dto.AdminRegisterDto;
import com.hostel.authservice.dto.LoginReq;
import com.hostel.authservice.dto.LoginRes;
import com.hostel.authservice.dto.LoginResWithTokenDto;
import com.hostel.authservice.dto.User;



@Service
public interface AdminAuthService {

	void register(AdminRegisterDto request);
	LoginResWithTokenDto login(LoginReq request);
	User loggedIn(String token);

}
