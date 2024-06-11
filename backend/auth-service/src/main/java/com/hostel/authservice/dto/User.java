package com.hostel.authservice.dto;

public class User {

	private long id;
	private String hostelName;
	private String name;
	private String email;
	private String phone;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User(long id, String hostelName, String name, String email, String phone) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
}
