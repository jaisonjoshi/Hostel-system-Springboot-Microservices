package com.hostel.adminservice.service;

import com.hostel.adminservice.dto.UpdateHostelDto;
import com.hostel.adminservice.model.Hostel;

public interface MainService {
	void createHostel(Hostel request);
	Hostel findByEmail(String email);
	Hostel updateHostel(UpdateHostelDto req);
}
