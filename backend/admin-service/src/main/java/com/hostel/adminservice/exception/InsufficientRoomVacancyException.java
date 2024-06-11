package com.hostel.adminservice.exception;

public class InsufficientRoomVacancyException extends RuntimeException{





	
	
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	public InsufficientRoomVacancyException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
	public String getMessage() {
		return message;
	}







	
}
