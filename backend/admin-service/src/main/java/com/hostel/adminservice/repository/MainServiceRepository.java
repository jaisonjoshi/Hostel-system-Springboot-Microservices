package com.hostel.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostel.adminservice.model.Hostel;


@Repository
public interface MainServiceRepository extends JpaRepository<Hostel, Long> {

	Hostel findByEmail(String email);
}
