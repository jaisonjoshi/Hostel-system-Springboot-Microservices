package com.hostel.authservice.dto;

public class ChangePasswordRequestDto {

	
	private long userid;
	private String password;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public ChangePasswordRequestDto() {
		super();
	}
	public ChangePasswordRequestDto(long userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
