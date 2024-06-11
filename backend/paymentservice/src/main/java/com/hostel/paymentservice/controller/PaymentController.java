package com.hostel.paymentservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.paymentservice.dto.PaymentReqDto;
import com.hostel.paymentservice.model.Payment;
import com.hostel.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	
	private PaymentService paymentService;
	
	
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}


	@GetMapping("/schedule")
	public ResponseEntity<?> paymentScheduler(){
		try {
			paymentService.paymentScheduler();
			return ResponseEntity.status(HttpStatus.CREATED).body("scheduled");

		}
		 catch(Exception e) {
			 System.out.println(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		 }
	}
	
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<Payment>> getAllPayments(@PathVariable int year, @PathVariable int month){
		return ResponseEntity.ok().body(paymentService.getAllPayments(year, month));
	}
	
	
	@GetMapping("/student/{id}")
	public ResponseEntity<List<Payment>> getPaymentsById(@PathVariable long id){
			System.out.println("helloooooooooiiii");
			return ResponseEntity.ok().body(paymentService.getPaymentsByStudentid(id));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> payAmount(@PathVariable long id){
		return ResponseEntity.accepted().body(paymentService.payAmount(id));
	}
	
	
	@GetMapping("/unpaid")
	public ResponseEntity<List<Payment>> getListOfUnpaidPayments(){
		return ResponseEntity.ok().body(paymentService.getListOfUnpaidPayments()	);
	}
	
	@GetMapping("/paymentschedulestatus")
	public ResponseEntity<?> paymentSchedulesStatus(){
		boolean scheduleStatus = paymentService.paymentSchedulesStatus();
		if(scheduleStatus) {
			return ResponseEntity.ok().body("scheduled");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not scheduled");
		}
	}
	
	
	

}
