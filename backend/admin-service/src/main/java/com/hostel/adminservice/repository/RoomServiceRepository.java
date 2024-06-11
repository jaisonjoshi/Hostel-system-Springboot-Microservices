package com.hostel.adminservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.adminservice.model.Room;

public interface RoomServiceRepository extends JpaRepository<Room, Long>{
	Room findByRoomNo(int roomNo);
	
	List<Room> findByVacancyGreaterThan(int num);
	
	List<Room> findByCategory(String category);
	List<Room> findByCapacity(int capacity);

	
}
