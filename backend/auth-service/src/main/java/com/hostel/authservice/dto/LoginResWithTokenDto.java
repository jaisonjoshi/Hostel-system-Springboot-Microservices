package com.hostel.authservice.dto;

public class LoginResWithTokenDto {

	
	private String token;
	private User user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public LoginResWithTokenDto() {
		super();
	}
	public LoginResWithTokenDto(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	
	
}
