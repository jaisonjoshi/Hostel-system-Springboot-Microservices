package com.hostel.registrationservice.dto;

public class CreateStudentRequestDto {

	private String name;
	private String email;
	private int roomNo;
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public CreateStudentRequestDto(String name, String email, String phone, String password, int roomNo) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roomNo = roomNo;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String phone;
	private String password;
}
