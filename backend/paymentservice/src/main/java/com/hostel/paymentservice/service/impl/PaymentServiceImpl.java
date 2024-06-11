package com.hostel.paymentservice.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostel.paymentservice.dto.ListOfStudentsDto;
import com.hostel.paymentservice.dto.PaymentReqDto;
import com.hostel.paymentservice.model.Payment;
import com.hostel.paymentservice.repository.PaymentServiceRepository;
import com.hostel.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	
	private PaymentServiceRepository paymentServiceRepository;
	

	
	
	
	public PaymentServiceImpl(PaymentServiceRepository paymentServiceRepository) {
		super();
		this.paymentServiceRepository = paymentServiceRepository;
	}
	
	@Override
	public boolean paymentSchedulesStatus() {
		LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();		
		List<Payment> payments = paymentServiceRepository.findByMonthAndYear(currentMonth, currentYear);
		System.out.println(payments.size());
		if(payments.size() != 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public List<Payment> getAllPayments(int month , int year){
		return paymentServiceRepository.findByMonthAndYear(month, year);
	}
	
	
	@Override
	public List<Payment> getPaymentsByStudentid(long id){
		return paymentServiceRepository.findByStudentid(id);
	}

	
	@Override
	public void paymentScheduler() {
		LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        
        try {
        	List<ListOfStudentsDto> students = paymentServiceRepository.getListToMakePaymentRequest();
            
            for(ListOfStudentsDto student: students) {
            	Payment payment = new Payment(student.getStudentId(), student.getName(), student.getRoomId(), student.getRoomNo(), student.getCapacity(), student.getCategory(), LocalDate.now(), student.getPrice(), currentMonth, currentYear, false );
            	paymentServiceRepository.save(payment);
            }
        	
        }catch(Exception e) {
        	throw e;
        }
        
	}
	
	
	@Override
	public Payment payAmount(long id) {
		Optional<Payment> payment = paymentServiceRepository.findById(id);
		if(payment != null) {
			Payment paymentObj = payment.get();
			paymentObj.setStatus(true);
			paymentServiceRepository.save(paymentObj);
			return paymentObj;
		}
		
		return null;
		
		
	
	}
	
	@Override
	public List<Payment> getListOfUnpaidPayments(){
		return paymentServiceRepository.findByStatusFalse();
	}


	



	
	
	
}
