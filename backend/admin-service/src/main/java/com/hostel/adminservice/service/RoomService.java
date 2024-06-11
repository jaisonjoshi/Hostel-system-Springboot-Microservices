package com.hostel.adminservice.service;

import java.util.List;

import com.hostel.adminservice.dto.CreateRoomDto;
import com.hostel.adminservice.model.Room;

public interface RoomService {

	Room createRoom(CreateRoomDto room);
	List<Room> getAllRooms();
	Room getRoom(int roomNo);
	int decrementRoomVacancy(int roomNo);
	boolean incrementRoomVacancy(int roomNo);

	List<Room> getVacantRooms();
	List<Room> getRoomsByCategory(String category);
	List<Room> getRoomsByCapacity(int capacity);
}
