package com.hostel.studentservice.service;

import java.util.List;

import com.hostel.studentservice.dto.ChangePasswordRequestDto;
import com.hostel.studentservice.dto.CreateStudentReqDto;
import com.hostel.studentservice.model.Student;

public interface StudentService {
	Student createStudent(CreateStudentReqDto student);
	
	void changePassword(ChangePasswordRequestDto req);
	
	List<Student> getAllStudents();
	Student findByEmail(String email);
	boolean delete(long id);
}
