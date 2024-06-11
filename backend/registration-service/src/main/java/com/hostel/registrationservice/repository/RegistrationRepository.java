package com.hostel.registrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostel.registrationservice.model.RegistrationItem;


@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationItem, Long>{

}
