package com.hostel.authservice.exception;

public class InvalidTokenException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	
	 public InvalidTokenException() {
	        super("Token expired or invalid.");
	    }

}
