package com.hostel.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.studentservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	
	Student findByEmail(String email);
}
