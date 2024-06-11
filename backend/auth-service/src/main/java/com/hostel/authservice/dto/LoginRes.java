package com.hostel.authservice.dto;

public class LoginRes {
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String hostelName;
	private String phone;
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String name;
	private String email;
	private String password;

	public LoginRes(long id, String hostelName, String phone, String name, String email, String password) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
}
