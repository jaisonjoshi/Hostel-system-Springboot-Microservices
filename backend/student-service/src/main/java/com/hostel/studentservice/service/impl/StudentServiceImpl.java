package com.hostel.studentservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hostel.studentservice.dto.ChangePasswordRequestDto;
import com.hostel.studentservice.dto.CreateStudentReqDto;
import com.hostel.studentservice.model.Student;
import com.hostel.studentservice.repository.StudentRepository;
import com.hostel.studentservice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	@Autowired
	private WebClient webClient;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student createStudent(CreateStudentReqDto student) {
		Student newStudent = new Student(student.getName(), student.getEmail(), student.getPhone(), student.getPassword(), student.getRoomNo());		
		return studentRepository.save(newStudent);
	}
	
	
	@Override
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	
	
	@Override
	public void changePassword(ChangePasswordRequestDto req) {
		Optional<Student> student = studentRepository.findById(req.getUserid());
		
		if(student.isPresent() ) {
			Student stud = student.get();
			stud.setPassword(req.getPassword());
			studentRepository.save(stud);
		}
	}
	
	@Override
	public Student findByEmail(String email) {
		Student user =  studentRepository.findByEmail(email);
		if(user==null) {
			throw new RuntimeException("User not found");
		}
		return user;
	}
	
	
	
	@Override
	public boolean delete(long id) {
		Optional<Student> student = studentRepository.findById(id); 
		if(student != null) {
			Student stud = student.get();
			boolean incremented = webClient.put()
					.uri("http://localhost:8001/api/hostel/rooms/increment/"+ stud.getRoomNo())
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
			if(incremented) {
				studentRepository.deleteById(stud.getId());
				return true;
			}
		}
		return false;
	}
}
