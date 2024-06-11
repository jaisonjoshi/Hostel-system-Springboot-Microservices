package com.hostel.adminservice.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.hostel.adminservice.dto.CreateRoomDto;
import com.hostel.adminservice.exception.InsufficientRoomVacancyException;
import com.hostel.adminservice.exception.ResourceNotFoundException;
import com.hostel.adminservice.model.Room;
import com.hostel.adminservice.repository.RoomServiceRepository;
import com.hostel.adminservice.service.RoomService;



@Service
public class RoomServiceImpl implements RoomService{
      
	
	
	private RoomServiceRepository roomServiceRepository;
	
	public RoomServiceImpl(RoomServiceRepository roomServiceRepository) {
		super();
		this.roomServiceRepository = roomServiceRepository;
	}

	
	
	//Function to create a room

	@Override
	public Room createRoom(CreateRoomDto room) {
		try {
			Room newRoom = new Room(room.getRoomNo(), room.getCategory(), room.getCapacity(), room.getCapacity(), room.getPrice());
			roomServiceRepository.save(newRoom);
			return newRoom;
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	
	//Function for getting all rooms.
	
	@Override
	public List<Room> getAllRooms(){
		return roomServiceRepository.findAll();
	}
	
	
	
	//Function to get the room based on room number.
	
	@Override
	public Room getRoom(int roomNo) {
		return roomServiceRepository.findByRoomNo(roomNo);
	}
	
	
	
	//Function to update the vacancy value of a room when a new registration happens
	
	@Override
	public int decrementRoomVacancy(int roomNo) {
		Room existingRoom = roomServiceRepository.findByRoomNo(roomNo);
		if(existingRoom == null) {
			throw new ResourceNotFoundException("Room", "Room No", roomNo);
		}
		if(existingRoom.getVacancy()<=0) {
			throw new InsufficientRoomVacancyException("No vacancy in Room No: "+ roomNo);
		}
		existingRoom.setVacancy(existingRoom.getVacancy()-1);
		roomServiceRepository.save(existingRoom);
		return existingRoom.getRoomNo();	
	}
	
	
	
	@Override
	public boolean incrementRoomVacancy(int roomNo) {
		Room existingRoom = roomServiceRepository.findByRoomNo(roomNo);
		if(existingRoom == null) {
			throw new ResourceNotFoundException("Room", "Room No", roomNo);
		}
		
		existingRoom.setVacancy(existingRoom.getVacancy()+1);
		roomServiceRepository.save(existingRoom);
		return true;	
	}
	
	
	//Function used to get the details of rooms having vacancy

	@Override
	public List<Room> getVacantRooms(){
		return roomServiceRepository.findByVacancyGreaterThan(0);
	}
	
	
	//Function used to get rooms based on category (Eg. AC/NON-AC)

	@Override
	public List<Room> getRoomsByCategory(String category){
		return roomServiceRepository.findByCategory(category);
	}
	
	
	//Function used to get rooms based on number of persons

	@Override
	public List<Room> getRoomsByCapacity(int capacity){
		return roomServiceRepository.findByCapacity(capacity);
	}
	
	
}
