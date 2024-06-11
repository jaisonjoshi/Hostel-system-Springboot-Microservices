package com.hostel.adminservice.service.impl;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostel.adminservice.dto.UpdateHostelDto;
import com.hostel.adminservice.exception.ResourceNotFoundException;
import com.hostel.adminservice.model.Hostel;
import com.hostel.adminservice.repository.MainServiceRepository;
import com.hostel.adminservice.service.MainService;


@Service
public class MainServciceImpl implements MainService {
		
	
	private MainServiceRepository mainServiceRepository;

	public MainServciceImpl(MainServiceRepository mainServiceRepository) {
		super();
		this.mainServiceRepository = mainServiceRepository;
	}
	
	
	
	
	//Function to save hostel
	
	@Override
	public void createHostel(Hostel req) {
		try {
			mainServiceRepository.save(req);
		}
		catch(RuntimeException e) {
			throw e;
		}
	}
	
	
	
	
	// This function is used to get the hostel details based on E-Mail id
	
	@Override
	public Hostel findByEmail(String email) {
		Hostel user =  mainServiceRepository.findByEmail(email);
		if(user==null) {
			throw new ResourceNotFoundException("Hostel", "email", email);
		}
		return user;
	}
	
	
	@Override
	public Hostel updateHostel(UpdateHostelDto req) {
		Optional<Hostel> hostel = mainServiceRepository.findById(req.getId());
		if(hostel != null) {
			Hostel existingHostel = hostel.get();
			existingHostel.setHostelName(req.getHostelName());
			existingHostel.setName(req.getName());
			existingHostel.setEmail(req.getEmail());
			existingHostel.setPhone(req.getPhone());
			
			mainServiceRepository.save(existingHostel);
			return existingHostel;
		}
		return null;
	}
	
	
	
}
