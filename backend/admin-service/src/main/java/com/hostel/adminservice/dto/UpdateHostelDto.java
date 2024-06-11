package com.hostel.adminservice.dto;

public class UpdateHostelDto {

	private long id;
	private String hostelName;
	private String name;
	private String email;
	private String phone;
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public UpdateHostelDto() {
		super();
	}
	
	
	public UpdateHostelDto(long id,String hostelName, String name, String email, String phone) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	
	
	
	
	
	
	
	
	
}
