package com.hostel.paymentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hostel.paymentservice.dto.ListOfStudentsDto;
import com.hostel.paymentservice.model.Payment;


@Repository
public interface PaymentServiceRepository extends JpaRepository<Payment, Long> {

	
	
	@Query(value = "SELECT s.id AS studentId, s.name AS name, s.email as email, s.phone as phone,r.id as roomId, r.room_no as roomNo, r.capacity AS capacity ,r.category as category , r.price as price " +
            "FROM student s " +
            "LEFT JOIN room r ON s.room_no = r.room_no", nativeQuery = true)	
	List<ListOfStudentsDto> getListToMakePaymentRequest();
	
	
	Payment findByIdAndMonthAndYear(long id, int month, int year);
	List<Payment> findByStatusFalse();
	List<Payment> findByStudentid(long id);
	List<Payment> findByMonthAndYear(int month, int year);
	
	
}
