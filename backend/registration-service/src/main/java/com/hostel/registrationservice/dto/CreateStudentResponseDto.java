package com.hostel.registrationservice.dto;

public class CreateStudentResponseDto extends CreateStudentRequestDto{

	
	private long id;
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public CreateStudentResponseDto(String name, String email, String phone, String password, int roomNo) {
		super(name, email, phone, password, roomNo);
	}

	
}
