package com.hostel.studentservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.studentservice.dto.ChangePasswordRequestDto;
import com.hostel.studentservice.dto.CreateStudentReqDto;
import com.hostel.studentservice.model.Student;
import com.hostel.studentservice.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	
	private StudentService studentService;
	
	
	
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok().body(studentService.getAllStudents());
	}


	@PostMapping("/register")
	public ResponseEntity<Student> createStudent(@RequestBody CreateStudentReqDto student) {
		return ResponseEntity.ok().body(studentService.createStudent(student));
	}
	
	
	@PutMapping("/changepassword")
	public void changePassword(@RequestBody ChangePasswordRequestDto req){
		studentService.changePassword(req);
	}
	
	
	@PostMapping("/details")
	public ResponseEntity<Student> getDetailsByEmail(@RequestBody String email) {
		try {
			return ResponseEntity.ok().body(studentService.findByEmail(email));
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id){
		boolean deleted = studentService.delete(id);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.CREATED).body("deletion successfull");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some error occured. try again later");
	}

	
	
}
