package com.hostel.paymentservice;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hostel.paymentservice.controller.PaymentController;
import com.hostel.paymentservice.model.Payment;
import com.hostel.paymentservice.service.PaymentService;


public class PaymentControllerTest {

	
	@Mock
	private PaymentService paymentService;
	
	@InjectMocks
	private PaymentController paymentController;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	 @Test
	    public void testGetPaymentsByStudentId() {
	        List<Payment> payments = Arrays.asList(new Payment(), new Payment());
	        
	        long studentId = 123;
	        when(paymentService.getPaymentsByStudentid(studentId)).thenReturn(payments);
	        
	        ResponseEntity<List<Payment>> response = paymentController.getPaymentsById(studentId);
	        
	        verify(paymentService).getPaymentsByStudentid(studentId);
	        
	        assertSame(payments, response.getBody());
	    }

}
