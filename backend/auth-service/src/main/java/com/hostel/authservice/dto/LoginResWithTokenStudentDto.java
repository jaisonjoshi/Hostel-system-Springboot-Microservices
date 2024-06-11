package com.hostel.authservice.dto;

public class LoginResWithTokenStudentDto {

	
	private String token;
	private StudentDto user;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public StudentDto getUser() {
		return user;
	}
	public void setUser(StudentDto user) {
		this.user = user;
	}
	
	
	
}
