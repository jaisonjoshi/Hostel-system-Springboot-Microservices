package com.hostel.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.paymentservice.dto.PaymentReqDto;
import com.hostel.paymentservice.model.Payment;


@Service
public interface PaymentService {
	
	List<Payment> getAllPayments(int month, int year);
	void paymentScheduler();
	Payment payAmount(long id);
	
	List<Payment> getListOfUnpaidPayments();
	List<Payment> getPaymentsByStudentid(long id);
	boolean paymentSchedulesStatus();
	
	
	
}
